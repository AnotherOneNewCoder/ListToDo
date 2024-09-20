package ru.zhogin.app.done.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun FloatingButtons(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {
    Column {
        FloatingActionButton(onClick = onDelete) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
        Spacer(modifier = Modifier.height(8.dp))
        FloatingActionButton(onClick = onDismiss) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "Close")
        }

    }
}