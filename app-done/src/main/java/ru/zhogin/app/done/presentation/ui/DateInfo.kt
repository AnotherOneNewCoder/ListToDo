package ru.zhogin.app.done.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.done.R
import ru.zhogin.app.tasks.common.util.Formater
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.uikit.Title3
import ru.zhogin.app.uikit.White

@Composable
internal fun DateInfo(
    modifier: Modifier = Modifier,
    selectedTask: TaskUI?,
) {
    Column(modifier) {
        Text(text = stringResource(R.string.created) + "${selectedTask?.date?.let { Formater.convertMillisecondsToTimestamp(it) }}",
                style = MaterialTheme.typography.Title3.copy(
                    color = White
                ))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.done) + "${selectedTask?.doneDate?.let { Formater.convertMillisecondsToTimestamp(it) }}",
            style = MaterialTheme.typography.Title3.copy(
                White
            ))

    }
}
