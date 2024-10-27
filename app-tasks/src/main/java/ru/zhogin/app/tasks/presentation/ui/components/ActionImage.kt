package ru.zhogin.app.tasks.presentation.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ActionImage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    image: Painter,
    contentDescription: String? = null,
) {
    Image(
        painter = image,
        contentDescription = contentDescription,
        modifier = modifier
            .background(backgroundColor)
            .fillMaxHeight()
            .padding(start = 16.dp)
            .clickable {
                onClick()
            }
    )
}