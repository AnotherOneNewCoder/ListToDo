package ru.zhogin.app.tasks.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.ActionIcon
import ru.zhogin.app.tasks.presentation.ui.components.PublicNotDoneTaskItem
import ru.zhogin.app.tasks.presentation.ui.components.SwipableItemWithActions
import ru.zhogin.app.tasks.presentation.ui.dialogs.AddTaskSheet
import ru.zhogin.app.tasks.presentation.ui.dialogs.DetailTaskSheet

@Composable
fun PublicNotDoneTaskScreen(
    modifier: Modifier = Modifier,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
) {

    val context = LocalContext.current
    when (state.isAddTaskSheetOpen) {
        true -> AddTaskSheet(
            onDismissRequest = { onEvent(PublicTasksListEvent.DismissPublicTasks) },
            state = state,
            newTask = newTask,
            onEvent = onEvent,
            modifier = modifier
        )
        else -> {}
    }

    when (state.isSelectedTaskSheetOpen) {
        true -> DetailTaskSheet(onDismissRequest = { onEvent(PublicTasksListEvent.DismissPublicTasks) },
            selectedTask = state.selectedTask, onEvent = onEvent)

        false -> {}
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
//        items(state.tasks) { task ->
//            Spacer(modifier = Modifier.height(10.dp))
//            PublicNotDoneTaskItem(
//                task = task,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        onEvent(PublicTasksListEvent.SelectPublicTask(task))
//                    }
//                    .padding(horizontal = 16.dp)
//            )
//        }
        itemsIndexed(
            items = state.tasks,
            key = { _, item: TaskUI -> item.id }
        ) { _, item: TaskUI ->
            SwipableItemWithActions(
                isRevealed = item.isOptionsRevealed,
                onExpanded = {
                    onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToTrue(item))
                },
                onCollapse = {
                    onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                },
                actions = {
                    ActionIcon(
                        onClick = {
                            onEvent(PublicTasksListEvent.DeletePublicTask)
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                                  },
                        icon = Icons.Filled.Delete,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            onEvent(PublicTasksListEvent.ChangeDoneStatusPublicTask(item))
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                                  },
                        icon = Icons.Filled.Done,
                        modifier = Modifier.fillMaxHeight(),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    ActionIcon(
                        onClick = {
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(context, "Shared!", Toast.LENGTH_SHORT).show()
                                  },
                        icon = Icons.Filled.Share,
                        modifier = Modifier.fillMaxHeight(),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                },
                ) {
                Spacer(modifier = Modifier.height(10.dp))
                PublicNotDoneTaskItem(
                    task = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(PublicTasksListEvent.SelectPublicTask(item))
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                onEvent(PublicTasksListEvent.OnAddNewPublicTaskClick)
            },


            ) {
            Icon(
                Icons.Filled.Add, contentDescription = "Add",
            )
        }
    }
}