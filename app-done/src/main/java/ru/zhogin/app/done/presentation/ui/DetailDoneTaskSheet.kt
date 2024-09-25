package ru.zhogin.app.done.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.done.presentation.event.PublicDoneTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.ui.components.TaskInfoSection
import ru.zhogin.app.uikit.Title1

@Composable
fun DetailDoneTaskSheet(
    onDismissRequest: () -> Unit,
    selectedTask: TaskUI?,
    onEvent: (PublicDoneTasksListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                if (selectedTask != null) {
                    Text(
                        text = selectedTask.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.Title1
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                DateInfo(selectedTask = selectedTask)
                Spacer(modifier = Modifier.height(16.dp))
                TaskInfoSection(
                    title = "Description",
                    value = selectedTask?.description ?: "",
                    icon = Icons.AutoMirrored.Rounded.List,
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    FloatingButtons(
                        onDismiss = onDismissRequest,
                        onDelete = {onEvent(PublicDoneTasksListEvent.DeletePublicDoneTask)}
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
            }

    }
}
