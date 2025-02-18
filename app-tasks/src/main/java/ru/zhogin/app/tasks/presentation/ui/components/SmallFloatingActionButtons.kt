package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.zhogin.app_settings.presentation.state.ColorsState

@Composable
internal fun SmallFloatingActionButtons(
    onEdit: () -> Unit,
    onDismiss: () -> Unit,
    colorState: ColorsState,
) {
    Column(
        modifier = Modifier.background(Color.Transparent)
    ) {

        GradientFloatingActionButton(
            gradientColors = listOf(
                colorState.firstGradientColor,
                colorState.secondGradientColor,
            ), onClick = onEdit
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit",
                tint = colorState.textColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        GradientFloatingActionButton(
            gradientColors = listOf(
                colorState.thirdGradientColor,
                colorState.fourthGradientColor,
            ), onClick = onDismiss
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "Done",
                tint = colorState.textColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


    }
}