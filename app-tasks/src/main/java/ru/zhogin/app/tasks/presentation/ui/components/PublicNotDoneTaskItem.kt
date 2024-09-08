package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.Title2,
            color = MaterialTheme.colorScheme.secondary,
            )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = task.date.toString(),
            style = MaterialTheme.typography.Title2,
            color = MaterialTheme.colorScheme.secondaryContainer)
    }
}