package ru.zhogin.app.tasks.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState

@Composable
fun PublicNotDoneTaskScreen(
    modifier: Modifier,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(state.tasks) { task ->
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
}