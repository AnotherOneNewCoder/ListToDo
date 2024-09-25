package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun SmallFloatingActionButtons(
    onEdit: () -> Unit,
    onDone: () -> Unit,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {
    Column(
        modifier = Modifier.background(Color.Transparent)
    ) {
        FloatingActionButton(
            onClick = onEdit,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Close")
        }
        Spacer(modifier = Modifier.height(8.dp))
        FloatingActionButton(onClick = onDone) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "Close")
        }
        Spacer(modifier = Modifier.height(8.dp))
        FloatingActionButton(
            onClick = onDelete,
            containerColor = MaterialTheme.colorScheme.error
        ) {
            Icon(
                imageVector = Icons.Filled.Delete, contentDescription = "Delete",
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        FloatingActionButton(
            onClick = onDismiss,
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
        }

    }
}