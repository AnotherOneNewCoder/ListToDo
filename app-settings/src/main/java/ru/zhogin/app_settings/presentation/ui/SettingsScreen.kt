package ru.zhogin.app_settings.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app_settings.R
import ru.zhogin.app_settings.presentation.event.ColorPickEvent
import ru.zhogin.app_settings.presentation.picker.ColorPicker2
import ru.zhogin.app_settings.presentation.state.ColorsState
import ru.zhogin.app_settings.presentation.ui.components.ColorRow
import ru.zhogin.app_settings.presentation.ui.components.GradientFloatingActionButton

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    colorState: ColorsState,
    onColorEvent: (ColorPickEvent) -> Unit,
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    when (colorState.isColorPickerSheetOpen) {
        true -> {
            ColorPicker2(
                //color = colorState.selectedColor?.color ?: BackgroundColor,
                color = colorState.selectedColor ?: BackgroundColor,
                onColorSelected = {
                    when (count) {
                        1 -> {
                            onColorEvent(ColorPickEvent.ChangeBackgroundColor(it))
                        }

                        2 -> {
                            onColorEvent(ColorPickEvent.ChangeBackgroundCardColor(it))
                        }

                        3 -> {
                            onColorEvent(ColorPickEvent.ChangeBorderColor(it))
                        }

                        4 -> {
                            onColorEvent(ColorPickEvent.ChangeTextColor(it))
                        }

                        5 -> {
                            onColorEvent(ColorPickEvent.ChangeHintColor(it))
                        }

                        6 -> {
                            onColorEvent(ColorPickEvent.ChangeBadgeColor(it))
                        }

                        7 -> {
                            onColorEvent(ColorPickEvent.ChangeFirstGradientColor(it))
                        }

                        8 -> {
                            onColorEvent(ColorPickEvent.ChangeSecondGradientColor(it))
                        }

                        9 -> {
                            onColorEvent(ColorPickEvent.ChangeThirdGradientColor(it))
                        }

                        10 -> {
                            onColorEvent(ColorPickEvent.ChangeFourthGradientColor(it))
                        }

                        else -> {}
                    }


                },
                onDismissRequest = {
                    onColorEvent(ColorPickEvent.OnCloseColorPicker)
                },
                colorsState = colorState
            )


        }

        false -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colorState.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.background_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.backgroundColor,
            onClick = {
                count = 1
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.backgroundColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.card_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.backgroundCardColor,
            onClick = {
                count = 2
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.backgroundCardColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.border_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.borderColor,
            onClick = {
                count = 3
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.borderColor))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.text_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.textColor,
            onClick = {
                count = 4
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.textColor))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.hint_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.hintColor,
            onClick = {
                count = 5
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.hintColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.badge_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.badgeColor,
            onClick = {
                count = 6
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.badgeColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.first_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.firstGradientColor,
            onClick = {
                count = 7
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.firstGradientColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.second_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.secondGradientColor,
            onClick = {
                count = 8
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.secondGradientColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.third_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.thirdGradientColor,
            onClick = {
                count = 9
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.thirdGradientColor))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.fourth_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.fourthGradientColor,
            onClick = {
                count = 10
                onColorEvent(ColorPickEvent.OnOpenColorPicker(colorState.fourthGradientColor))
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
                .height(48.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            colorState.thirdGradientColor,
                            colorState.fourthGradientColor,
                        )
                    ), CircleShape
                )
                .clip(RoundedCornerShape(100))
                .border(0.5.dp, colorState.borderColor, CircleShape)
                .clickable(
                    enabled = true,
                    onClick = {
                        onColorEvent(ColorPickEvent.ResetToDefault)
                    }
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.reset_to_default),
                textAlign = TextAlign.Center,
                color = colorState.textColor,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, end = 16.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomEnd
        ) {
            GradientFloatingActionButton(gradientColors = listOf(
                colorState.firstGradientColor,
                colorState.secondGradientColor,
            ), onClick = { onColorEvent(ColorPickEvent.SaveColor) }) {
                Icon(
                    Icons.Filled.Done, contentDescription = "Save", tint = colorState.textColor,
                )
            }
        }
    }
}