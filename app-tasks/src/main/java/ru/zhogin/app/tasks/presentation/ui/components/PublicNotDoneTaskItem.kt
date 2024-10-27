package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.common.util.Formater
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.DarkNavy
import ru.zhogin.app.uikit.Text2
import ru.zhogin.app.uikit.White

@Composable
fun PublicNotDoneTaskItem(
    task: TaskUI,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(DarkNavy, RoundedCornerShape(12.dp))
            .border(0.5.dp, Blue, RoundedCornerShape(12.dp))

        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.Text2,
            color = White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(16.dp))
        if (task.reminder) {
            Text(
                text = Formater.convertMillsToDate(task.reminderDate),
                style = MaterialTheme.typography.Text2,
                color = Blue
            )
            Spacer(modifier = Modifier.width(8.dp))
        } else {
            Text(
                text = Formater.convertMillisecondsToTimestamp(task.date),
                style = MaterialTheme.typography.Text2,
                color = Blue
            )
            Spacer(modifier = Modifier.width(8.dp))
        }



    }
}