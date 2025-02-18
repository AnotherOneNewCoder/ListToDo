package ru.zhogin.app_settings.presentation.state

import androidx.compose.ui.graphics.Color
import ru.zhogin.app.uikit.BackgroundCardColor
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app.uikit.BadgeColor
import ru.zhogin.app.uikit.BorderColor
import ru.zhogin.app.uikit.FirstGradientColor
import ru.zhogin.app.uikit.FourthGradientColor
import ru.zhogin.app.uikit.HintColor
import ru.zhogin.app.uikit.SecondGradientColor
import ru.zhogin.app.uikit.TextColor
import ru.zhogin.app.uikit.ThirdGradientColor

data class ColorsState(
    val selectedColor: Color? = null,
    val backgroundColor : Color = BackgroundColor,
    val backgroundCardColor : Color = BackgroundCardColor,
    val borderColor : Color = BorderColor,
    val textColor : Color = TextColor,
    val hintColor  : Color = HintColor,
    val badgeColor : Color = BadgeColor,
    val firstGradientColor : Color = FirstGradientColor,
    val secondGradientColor : Color = SecondGradientColor,
    val thirdGradientColor : Color = ThirdGradientColor,
    val fourthGradientColor : Color = FourthGradientColor,
    val isColorPickerSheetOpen: Boolean = false,
)




