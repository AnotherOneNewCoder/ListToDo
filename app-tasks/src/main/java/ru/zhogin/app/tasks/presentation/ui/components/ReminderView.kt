package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.Blue

@Composable
internal fun ReminderView(
    modifier: Modifier = Modifier,
    onClickChooseDate: () -> Unit,
    onClickChooseTime: () -> Unit,
    date: String,
    time: String,
    ) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(20.dp))
                .border(width = 1.dp, Blue, RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = onClickChooseDate) {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = "Choose date",
                    tint = Blue
                )
            }

            Text(text = date, color = Blue)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(width = 1.dp, Blue, RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = onClickChooseTime) {
                Icon(
                    imageVector = Icons.Rounded.Notifications, contentDescription = "Choose time",
                    tint = Blue
                )
            }
            Text(text = time, color = Blue)
        }
    }

}