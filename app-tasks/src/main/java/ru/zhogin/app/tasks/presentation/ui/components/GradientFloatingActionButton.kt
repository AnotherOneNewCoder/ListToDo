package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientFloatingActionButton(
    size: Dp = 56.dp,
    gradientColors: List<Color>,
    elevation: Dp = 0.dp,
    onClick: () -> Unit,
    content: @Composable() (BoxScope.() -> Unit)
) {
    val shape = CircleShape
    Surface(
        modifier = Modifier
            .size(size)
            .clip(shape)
            .background(Brush.horizontalGradient(gradientColors))
            .clickable { onClick() }
        ,
        color = Color.Transparent,
        shape = shape,
        tonalElevation = elevation,
        shadowElevation = elevation,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                contentAlignment = Alignment.Center,
                content = content
            )

        }
    )
}
@Preview
@Composable
fun GradientFloatingActionButtonPreview() {
    val gradientColors = listOf(Color(0xFF7DFF6C), Color(0xFF008B80))
    GradientFloatingActionButton(
        gradientColors = gradientColors,
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add icon",
            tint = Color.White
        )
    }
}