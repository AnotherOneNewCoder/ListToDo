package ru.zhogin.app.tasks.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.PublicNotDoneTaskItem
import ru.zhogin.app.tasks.presentation.ui.dialogs.AddTaskSheet
import ru.zhogin.app.tasks.presentation.ui.dialogs.DetailTaskSheet

@Composable
fun PublicNotDoneTaskScreen(
    modifier: Modifier = Modifier,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
) {


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
        items(state.tasks) { task ->
            Spacer(modifier = Modifier.height(10.dp))
            PublicNotDoneTaskItem(
                task = task,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(PublicTasksListEvent.SelectPublicTask(task))
                    }
                    .padding(horizontal = 16.dp)
            )
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