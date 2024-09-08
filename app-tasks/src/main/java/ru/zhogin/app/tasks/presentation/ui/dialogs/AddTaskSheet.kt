package ru.zhogin.app.tasks.presentation.ui.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.TaskTextField
import ru.zhogin.app.uikit.Title1

@Composable
internal fun AddTaskSheet(
    onDismissRequest: () -> Unit,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Create new task: ",
                    style = MaterialTheme.typography.Title1
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.title ?: "",
                    placeHolder = "Title",
                    error = state.validationTitleError,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnTitleChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.description ?: "",
                    placeHolder = "Description",
                    error = null,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnDescriptionChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    onEvent(PublicTasksListEvent.SavePublicTask)
                })
                {
                    Text(text = "Save")
                }

            }
            IconButton(onClick = onDismissRequest) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Dismiss",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}