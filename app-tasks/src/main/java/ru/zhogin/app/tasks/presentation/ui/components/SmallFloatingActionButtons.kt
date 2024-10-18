package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
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
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier.background(Color.Transparent)
    ) {
        FloatingActionButton(
            onClick = onEdit,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(onClick = onDismiss) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
        }
        Spacer(modifier = Modifier.height(8.dp))


    }
}