package ru.zhogin.app_settings.presentation.event

import androidx.compose.ui.graphics.Color


sealed interface ColorPickEvent {
    data class OnOpenColorPicker(val color: Color): ColorPickEvent
    data object OnCloseColorPicker : ColorPickEvent
    data object SaveColor : ColorPickEvent
    data object ResetToDefault : ColorPickEvent
    data class ChangeBackgroundColor(val color: Color) : ColorPickEvent
    data class ChangeBackgroundCardColor(val color: Color) : ColorPickEvent
    data class ChangeBorderColor(val color: Color) : ColorPickEvent
    data class ChangeTextColor(val color: Color) : ColorPickEvent
    data class ChangeHintColor(val color: Color) : ColorPickEvent
    data class ChangeBadgeColor(val color: Color) : ColorPickEvent
    data class ChangeFirstGradientColor(val color: Color) : ColorPickEvent
    data class ChangeSecondGradientColor(val color: Color) : ColorPickEvent
    data class ChangeThirdGradientColor(val color: Color) : ColorPickEvent
    data class ChangeFourthGradientColor(val color: Color) : ColorPickEvent
}




