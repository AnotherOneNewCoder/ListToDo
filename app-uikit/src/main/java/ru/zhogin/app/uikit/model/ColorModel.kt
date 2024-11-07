package ru.zhogin.app.uikit.model

import androidx.compose.ui.graphics.Color
import ru.zhogin.app.uikit.BackgroundColor

data class ColorModel(
    val colorName: String = "Background",
    val color: Color = BackgroundColor,
)
