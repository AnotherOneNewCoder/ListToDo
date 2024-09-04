package ru.zhogin.app.tasks.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.uikit.Title2

@Composable
internal fun PublicNotDoneTaskItem(
    task: TaskUI,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.Title2,
            color = MaterialTheme.colorScheme.secondary,
            )
    }
}