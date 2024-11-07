package ru.zhogin.app.done.presentation.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import ru.zhogin.app.done.R

import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.ui.components.GradientFloatingActionButton
import ru.zhogin.app.tasks.presentation.ui.components.TaskInfoSection
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.DarkNavy
import ru.zhogin.app.uikit.Navy
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.White
import ru.zhogin.app.uikit.state.ColorsState

@Composable
fun DetailDoneTaskSheet(
    onDismissRequest: () -> Unit,
    selectedTask: TaskUI?,
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
                Spacer(modifier = Modifier.height(30.dp))
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
                DateInfo(selectedTask = selectedTask)
                Spacer(modifier = Modifier.height(16.dp))
                TaskInfoSection(
                    title = stringResource(R.string.description),
                    value = selectedTask?.description ?: "",
                    modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth(),
                    colorState = colorState,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    GradientFloatingActionButton(gradientColors = listOf(
                        colorState.hintColor,
                        colorState.backgroundColor,
                    ), onClick = onDismissRequest) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = "Done", tint = colorState.textColor)
                    }
                }

            }
            }

    }
}
