package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.common.ClipboardManager
import ru.zhogin.app.uikit.Number
import ru.zhogin.app.uikit.TabText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskInfoSection(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    var copy by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.Number,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        onLongClick = { copy = true },
                        onClick = {},
                    )
                    .verticalScroll(rememberScrollState())
                ,
                style = MaterialTheme.typography.TabText,
                color = MaterialTheme.colorScheme.secondary
            )
            if (copy) {
                ClipboardManager(text = value)
                copy = false
            }
        }
    }
}