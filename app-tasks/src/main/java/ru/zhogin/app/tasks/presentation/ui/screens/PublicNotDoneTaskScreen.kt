package ru.zhogin.app.tasks.presentation.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.R
import ru.zhogin.app.tasks.presentation.alarm.cancelAlarm
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.ActionImage
import ru.zhogin.app.tasks.presentation.ui.components.GradientFloatingActionButton
import ru.zhogin.app.tasks.presentation.ui.components.PublicNotDoneTaskItem
import ru.zhogin.app.tasks.presentation.ui.components.SwipableItemWithActions
import ru.zhogin.app.tasks.presentation.ui.dialogs.AddTaskSheet
import ru.zhogin.app.tasks.presentation.ui.dialogs.DetailTaskSheet
import ru.zhogin.app.uikit.GradientBlue
import ru.zhogin.app.uikit.GradientPurple
import ru.zhogin.app.uikit.Navy
import ru.zhogin.app.uikit.White
import ru.zhogin.app.uikit.state.ColorsState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PublicNotDoneTaskScreen(
    modifier: Modifier = Modifier,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    colorState: ColorsState,
) {

    val context = LocalContext.current
    when (state.isAddTaskSheetOpen) {
        true -> AddTaskSheet(
            onDismissRequest = { onEvent(PublicTasksListEvent.DismissPublicTasks) },
            state = state,
            newTask = newTask,
            onEvent = onEvent,
            modifier = modifier,
            context = context,
            colorState = colorState,
        )

        else -> {}
    }

    when (state.isSelectedTaskSheetOpen) {
        true -> DetailTaskSheet(
            onDismissRequest = { onEvent(PublicTasksListEvent.DismissPublicTasks) },
            selectedTask = state.selectedTask, onEvent = onEvent,
            colorState = colorState,
        )

        false -> {}
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colorState.backgroundColor)

    ) {
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
                    ActionImage(
                        onClick = {
                            cancelAlarm(context, item)
                            onEvent(PublicTasksListEvent.DeletePublicTask)
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(
                                context,
                                context.getString(R.string.deleted) + " " + item.title + "!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        image = painterResource(id = R.drawable.ic_delete),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(30.dp)
                    )
                    ActionImage(
                        onClick = {
                            onEvent(PublicTasksListEvent.ChangeDoneStatusPublicTask(item))
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(
                                context,
                                context.getString(R.string.done),
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        image = painterResource(id = R.drawable.ic_done),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(30.dp)
                    )
                    ActionImage(
                        onClick = {
                            onEvent(PublicTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                            Toast.makeText(
                                context,
                                context.getString(R.string.shared), Toast.LENGTH_SHORT
                            ).show()
                        },
                        image = painterResource(id = R.drawable.ic_share),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(30.dp)
                    )
                },
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                PublicNotDoneTaskItem(
                    task = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        //.height(45.dp)
                        .background(colorState.backgroundColor)
                        .clickable {
                            onEvent(PublicTasksListEvent.SelectPublicTask(item))
                        }
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    colorState = colorState,
                )
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomEnd
    ) {
        GradientFloatingActionButton(gradientColors = listOf(
            colorState.firstGradientColor,
            colorState.secondGradientColor,
        ), onClick = { onEvent(PublicTasksListEvent.OnAddNewPublicTaskClick) }) {
            Icon(
                Icons.Filled.Add, contentDescription = "Add", tint = colorState.textColor,
            )
        }
    }
}