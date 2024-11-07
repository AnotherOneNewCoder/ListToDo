package ru.zhogin.app.uikit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zhogin.app.uikit.BackgroundCardColor
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app.uikit.BadgeColor
import ru.zhogin.app.uikit.BorderColor
import ru.zhogin.app.uikit.FirstGradientColor
import ru.zhogin.app.uikit.HintColor
import ru.zhogin.app.uikit.SecondGradientColor
import ru.zhogin.app.uikit.TextColor
import ru.zhogin.app.uikit.event.ColorPickEvent
import ru.zhogin.app.uikit.model.ColorModel
import ru.zhogin.app.uikit.state.ColorsState
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor() : ViewModel() {
    var state = MutableStateFlow(ColorsState())
        private set

    var newColor: ColorModel? by mutableStateOf(null)


    fun onEvent(event: ColorPickEvent) {
        when (event) {
            ColorPickEvent.OnCloseColorPicker -> {
                state.update {
                    state.value.copy(isColorPickerSheetOpen = false)
                }
            }

            ColorPickEvent.OnOpenColorPicker -> {
                state.update {
                    state.value.copy(isColorPickerSheetOpen = true)
                }
            }

            ColorPickEvent.ResetToDefault -> {
                state.update {
                    state.value.copy(
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
            }


            is ColorPickEvent.SelectedColor -> {
                state.update {
                    state.value.copy(
                        selectedColor = newColor,
                        isColorPickerSheetOpen = true,
                    )
                }

            }

            is ColorPickEvent.SaveColor -> {
                viewModelScope.launch {
                        when (state.value.selectedColor?.colorName) {
                            "Background" -> {
                                state.update {
                                    state.value.selectedColor?.color?.let { it1 ->
                                        state.value.copy(
                                            backgroundColor = it1,
                                            isColorPickerSheetOpen = false,
                                        )
                                    }!!
                                }
                                delay(500L)
                                newColor = null
                            }
                            else -> {}
                        }

                }
            }

            is ColorPickEvent.ChangeBackgroundColor -> {
                state.update {
                    state.value.copy(
                        backgroundColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeBackgroundCardColor -> {
                state.update {
                    state.value.copy(
                        backgroundCardColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }

            is ColorPickEvent.ChangeBadgeColor -> {
                state.update {
                    state.value.copy(
                        badgeColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeBorderColor -> {
                state.update {
                    state.value.copy(
                        borderColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeFirstGradientColor -> {
                state.update {
                    state.value.copy(
                        firstGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeHintColor -> {
                state.update {
                    state.value.copy(
                        hintColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeSecondGradientColor -> {
                state.update {
                    state.value.copy(
                        secondGradientColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
            is ColorPickEvent.ChangeTextColor -> {
                state.update {
                    state.value.copy(
                        textColor = event.color,
                        isColorPickerSheetOpen = false,
                    )
                }
            }
        }
    }
}

