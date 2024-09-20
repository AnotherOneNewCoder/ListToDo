package ru.zhogin.app.done.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.done.presentation.event.PublicDoneTasksListEvent
import ru.zhogin.app.done.presentation.state.PublicDoneTasksListState


@Composable
fun PublicDoneTasksScreen(
    modifier: Modifier,
    state: PublicDoneTasksListState,
    onEvent: (PublicDoneTasksListEvent) -> Unit,
) {

    when (state.isSelectedTaskSheetOpen) {
        true -> DetailDoneTaskSheet(onDismissRequest = { onEvent(PublicDoneTasksListEvent.DismissPublicDoneTasks) },
            selectedTask = state.selectedTask, onEvent = onEvent)

        false -> {}
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(state.tasks) { task ->
            Spacer(modifier = Modifier.height(10.dp))
            PublicDoneTaskItem(
                task = task,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(PublicDoneTasksListEvent.SelectPublicDoneTask(task))
                    }
                    .padding(horizontal = 16.dp)
            )


        }
    }

}