package ru.zhogin.app.done.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.done.presentation.event.PublicDoneTasksListEvent
import ru.zhogin.app.done.presentation.state.PublicDoneTasksListState
import ru.zhogin.app.tasks.R
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.ui.components.ActionImage
import ru.zhogin.app.tasks.presentation.ui.components.PublicNotDoneTaskItem
import ru.zhogin.app.tasks.presentation.ui.components.SwipableItemWithActions
import ru.zhogin.app_settings.presentation.state.ColorsState


@Composable
fun PublicDoneTasksScreen(
    modifier: Modifier,
    state: PublicDoneTasksListState,
    onEvent: (PublicDoneTasksListEvent) -> Unit,
    colorState: ColorsState,
) {
    val context = LocalContext.current
    when (state.isSelectedTaskSheetOpen) {
        true -> DetailDoneTaskSheet(onDismissRequest = { onEvent(PublicDoneTasksListEvent.DismissPublicDoneTasks) },
            selectedTask = state.selectedTask, colorState = colorState)

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
                    onEvent(PublicDoneTasksListEvent.OnOptionsRevealedChangedToTrue(item))
                },
                onCollapse = {
                    onEvent(PublicDoneTasksListEvent.OnOptionsRevealedChangedToFalse(item))
                },
                actions = {
                    ActionImage(
                        onClick = {
                            onEvent(PublicDoneTasksListEvent.DeletePublicDoneTask)
                            onEvent(PublicDoneTasksListEvent.OnOptionsRevealedChangedToFalse(item))
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
                            onEvent(PublicDoneTasksListEvent.OnOptionsRevealedChangedToFalse(item))
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
                        .background(colorState.backgroundColor)
                        .clickable {
                            onEvent(PublicDoneTasksListEvent.SelectPublicDoneTask(item))
                        }
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    colorState = colorState
                )
            }
        }

    }

}