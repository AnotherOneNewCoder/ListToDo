package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.tasks.common.ClipboardManager
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app_settings.presentation.state.ColorsState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskInfoSection(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    colorState: ColorsState
) {
    var copy by rememberSaveable {
        mutableStateOf(false)
    }
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.Text1.copy(
                color = colorState.hintColor
            ),

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(
                    onLongClick = { copy = true },
                    onClick = {},
                )
                .verticalScroll(rememberScrollState()),
            style = MaterialTheme.typography.Text1.copy(
                color = colorState.textColor
            ),
        )
        if (copy) {
            ClipboardManager(text = value)
            copy = false
        }
    }
}