package ru.zhogin.app.tasks.presentation.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.tasks.R
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.ui.components.SmallFloatingActionButtons
import ru.zhogin.app.tasks.presentation.ui.components.TaskInfoSection
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.DarkNavy
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.White
import ru.zhogin.app.uikit.state.ColorsState

@Composable
fun DetailTaskSheet(
    onDismissRequest: () -> Unit,
    selectedTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    modifier: Modifier = Modifier,
    colorState: ColorsState
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
                containerColor = colorState.backgroundCardColor
            ),
            border = BorderStroke(0.5.dp, colorState.borderColor)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (selectedTask != null) {
                    Text(
                        text = selectedTask.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.Title1.copy(
                            color = colorState.textColor
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TaskInfoSection(
                    title = stringResource(id = R.string.description) ,
                    value = selectedTask?.description ?: "",
                    modifier = Modifier.fillMaxHeight(0.7f).fillMaxWidth(),
                    colorState = colorState,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    SmallFloatingActionButtons(
                        onEdit = {
                            selectedTask?.let {
                            onEvent(PublicTasksListEvent.EditPublicTask(it))
                        } },
                        onDismiss = onDismissRequest,
                        )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
