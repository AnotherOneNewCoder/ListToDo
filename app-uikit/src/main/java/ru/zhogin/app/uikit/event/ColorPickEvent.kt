package ru.zhogin.app.uikit.event

import androidx.compose.ui.graphics.Color
import ru.zhogin.app.uikit.model.ColorModel


sealed interface ColorPickEvent {
    data object OnOpenColorPicker : ColorPickEvent
    data object OnCloseColorPicker : ColorPickEvent
    data class SelectedColor(val colorModel: ColorModel) : ColorPickEvent
    data class SaveColor(val colorModel: ColorModel) : ColorPickEvent
    data object ResetToDefault : ColorPickEvent
    data class ChangeBackgroundColor(val color: Color) : ColorPickEvent
    data class ChangeBackgroundCardColor(val color: Color) : ColorPickEvent
    data class ChangeBorderColor(val color: Color) : ColorPickEvent
    data class ChangeTextColor(val color: Color) : ColorPickEvent
    data class ChangeHintColor(val color: Color) : ColorPickEvent
    data class ChangeBadgeColor(val color: Color) : ColorPickEvent
    data class ChangeFirstGradientColor(val color: Color) : ColorPickEvent
    data class ChangeSecondGradientColor(val color: Color) : ColorPickEvent
}




