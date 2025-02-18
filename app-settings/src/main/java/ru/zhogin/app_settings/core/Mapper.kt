package ru.zhogin.app_settings.core

import androidx.compose.ui.graphics.Color
import ru.zhogin.app_settings.data.model.SettingsData
import ru.zhogin.app_settings.presentation.state.ColorsState

internal fun SettingsData.toColorState(): ColorsState {
    return ColorsState(
        backgroundColor = Color(backgroundColor),
        badgeColor = Color(badgeColor),
        backgroundCardColor = Color(backgroundCardColor),
        borderColor = Color(borderColor),
        textColor = Color(textColor),
        hintColor = Color(hintColor),
        firstGradientColor = Color(firstGradientColor),
        secondGradientColor = Color(secondGradientColor)
    )
}