package ru.zhogin.app.uikit.state

import androidx.compose.ui.graphics.Color
import ru.zhogin.app.uikit.*
import ru.zhogin.app.uikit.model.ColorModel

data class ColorsState(
    val selectedColor: ColorModel? = null,
    val backgroundColor : Color = BackgroundColor,
    val backgroundCardColor : Color = BackgroundCardColor,
    val borderColor : Color = BorderColor,
    val textColor : Color = TextColor,
    val hintColor  : Color = HintColor ,
    val badgeColor : Color = BadgeColor,
    val firstGradientColor : Color = FirstGradientColor,
    val secondGradientColor : Color = SecondGradientColor,
    val isColorPickerSheetOpen: Boolean = false,
)




