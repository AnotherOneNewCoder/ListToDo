package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun EditRow(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDoneClick: () -> Unit,
) {
    Row(modifier) {
        FilledTonalIconButton(
            onClick = onEditClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        ) {
            Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit")
        }
        FilledTonalIconButton(
            onClick = onDeleteClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
            )
        ) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
        }
        FilledTonalIconButton(
            onClick = onDoneClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        ) {
            Icon(imageVector = Icons.Rounded.Done, contentDescription = "Done")
        }
    }
}