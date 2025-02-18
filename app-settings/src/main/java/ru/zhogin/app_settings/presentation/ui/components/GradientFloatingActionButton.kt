package ru.zhogin.app_settings.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientFloatingActionButton(
    size: Dp = 56.dp,
    gradientColors: List<Color>,
    elevation: Dp = 0.dp,
    onClick: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
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
