package ru.zhogin.app_settings.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
import ru.zhogin.app_settings.data.model.SettingsData
import ru.zhogin.app_settings.domain.DataStoreManager
import ru.zhogin.app_settings.presentation.event.ColorPickEvent
import ru.zhogin.app_settings.presentation.model.ColorModel
import ru.zhogin.app_settings.presentation.state.ColorsState
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
//    var state = MutableStateFlow(ColorsState())
//        private set

//    val state : StateFlow<ColorsState> = dataStoreManager.getSettings()
//        .map { it.toColorState() }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ColorsState())

    private val _state = MutableStateFlow(ColorsState())
    val state = _state
        .onStart {
            observeColors()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    var newColor: ColorModel? by mutableStateOf(null)

    var newColor2: Color? by mutableStateOf(null)
    private var colorJob: Job? = null

    fun onEvent(event: ColorPickEvent) {
        when (event) {
            ColorPickEvent.OnCloseColorPicker -> {
                _state.update {
                    _state.value.copy(isColorPickerSheetOpen = false)
                }
            }

            is ColorPickEvent.OnOpenColorPicker -> {
                newColor2 = event.color
                _state.update {
                    _state.value.copy(
                        selectedColor = event.color,
                        isColorPickerSheetOpen = true
                    )
                }
            }

            ColorPickEvent.ResetToDefault -> {
                _state.update {
                    _state.value.copy(
                        selectedColor = null,
                        backgroundColor = BackgroundColor,
                        backgroundCardColor = BackgroundCardColor,
                        borderColor = BorderColor,
                        textColor = TextColor,
                        hintColor = HintColor,
                        badgeColor = BadgeColor,
                        firstGradientColor = FirstGradientColor,
                        secondGradientColor = SecondGradientColor,
                        isColorPickerSheetOpen = false,
                    )
                }
                viewModelScope.launch {
                    dataStoreManager.saveSettings(
                        SettingsData(
                            //backgroundColor = "0xFF172157",
                            backgroundColor = BackgroundColor.toArgb(),
                            backgroundCardColor = BackgroundCardColor.toArgb(),
                            borderColor = BorderColor.toArgb(),
                            textColor = TextColor.toArgb(),
                            hintColor = HintColor.toArgb(),
                            badgeColor = BadgeColor.toArgb(),
                            firstGradientColor = FirstGradientColor.toArgb(),
                            secondGradientColor = SecondGradientColor.toArgb(),
                            thirdGradientColor = ThirdGradientColor.toArgb(),
                            fourthGradientColor = FourthGradientColor.toArgb(),
                        )
                    )
                }
            }


            is ColorPickEvent.SelectedColor -> {
                _state.update {
                    _state.value.copy(
                        selectedColor = newColor2,
                        isColorPickerSheetOpen = true,
                    )
                }

            }

            is ColorPickEvent.SaveColor -> {
                viewModelScope.launch {
                    dataStoreManager.saveSettings(
                        SettingsData(
                            backgroundColor = _state.value.backgroundColor.toArgb(),
                            backgroundCardColor = _state.value.backgroundCardColor.toArgb(),
                            borderColor = _state.value.borderColor.toArgb(),
                            textColor = _state.value.textColor.toArgb(),
                            hintColor = _state.value.hintColor.toArgb(),
                            badgeColor = _state.value.badgeColor.toArgb(),
                            firstGradientColor = _state.value.firstGradientColor.toArgb(),
                            //secondGradientColor = _state.value.secondGradientColor.value.toLong(),
                            secondGradientColor = _state.value.secondGradientColor.toArgb(),
                            thirdGradientColor = _state.value.thirdGradientColor.toArgb(),
                            fourthGradientColor = _state.value.fourthGradientColor.toArgb(),
                        )
                    )

                }
            }

            is ColorPickEvent.ChangeBackgroundColor -> {
                _state.update {
                    _state.value.copy(
                        backgroundColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }

            }

            is ColorPickEvent.ChangeBackgroundCardColor -> {
                _state.update {
                    _state.value.copy(
                        backgroundCardColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeBadgeColor -> {
                _state.update {
                    _state.value.copy(
                        badgeColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeBorderColor -> {
                _state.update {
                    _state.value.copy(
                        borderColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeFirstGradientColor -> {
                _state.update {
                    _state.value.copy(
                        firstGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeHintColor -> {
                _state.update {
                    _state.value.copy(
                        hintColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeSecondGradientColor -> {
                _state.update {
                    _state.value.copy(
                        secondGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeTextColor -> {
                _state.update {
                    _state.value.copy(
                        textColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeFourthGradientColor -> {
                _state.update {
                    _state.value.copy(
                        fourthGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeThirdGradientColor -> {
                _state.update {
                    _state.value.copy(
                        thirdGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
        }
    }

    private fun observeColors() {
        colorJob?.cancel()
        colorJob = dataStoreManager.getSettings()
            .onEach { settingsData ->
                _state.update {
                    it.copy(

                        backgroundColor = Color(settingsData.backgroundColor),
                        badgeColor = Color(settingsData.badgeColor),
                        backgroundCardColor = Color(settingsData.backgroundCardColor),
                        borderColor = Color(settingsData.borderColor),
                        textColor = Color(settingsData.textColor),
                        hintColor = Color(settingsData.hintColor),
                        firstGradientColor = Color(settingsData.firstGradientColor),
                        secondGradientColor = Color(settingsData.secondGradientColor),

//                        backgroundColor = Color(settingsData.backgroundColor.toLong()),
//                        badgeColor = Color(settingsData.badgeColor.toLong()),
//                        backgroundCardColor = Color(settingsData.backgroundCardColor.toLong()),
//                        borderColor = Color(settingsData.borderColor.toLong()),
//                        textColor = Color(settingsData.textColor.toLong()),
//                        hintColor = Color(settingsData.hintColor.toLong()),
//                        firstGradientColor = Color(settingsData.firstGradientColor.toLong()),
//                        secondGradientColor = Color(settingsData.secondGradientColor.toLong()),
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}

