package ru.zhogin.app_settings.presentation.picker

import android.graphics.Color.HSVToColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.uikit.BackgroundCardColor
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.R
import ru.zhogin.app.uikit.White
import ru.zhogin.app_settings.presentation.state.ColorsState
import kotlin.math.roundToInt


@Composable
fun ColorPicker2(
    modifier: Modifier = Modifier,
    color: Color,
    colorsState: ColorsState,
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit,
) {

    val density = LocalDensity.current
    val paddingHorizontal = 30.dp

    // ========= color hue =========
    val colorMapWidth =
        LocalConfiguration.current.screenWidthDp.dp - paddingHorizontal - paddingHorizontal
    val colorMapSelectionRadius = 25.dp

    val colorMapOffsetPx = remember {
        mutableFloatStateOf(
            with(density) {

                // default position of selector
               // -colorMapSelectionRadius.toPx()
                -colorMapSelectionRadius.toPx() +  colorMapWidth.toPx() / 360f * getHeuFromColor(color)


            }
        )
    }

    // ========= color saturation =========
    val saturationSelectorRadius = 10.dp



    val saturation = remember {
        mutableFloatStateOf(getSaturationFromColor(color))
    }
    val saturationOffsetPx = remember {
        mutableFloatStateOf(
            with(density) {
                // the saturation default value is 1, so the selector default position should be on the right side
                //(colorMapWidth - saturationSelectorRadius).toPx()
                (colorMapWidth - saturationSelectorRadius).toPx() - (1f - saturation.floatValue)*(colorMapWidth).toPx()
            }
        )
    }


    // ========= color saturation =========

    // ========= color lightness =========
    val lightness = remember {
        mutableFloatStateOf(getLightnessFromColor(color))
    }
    val lightnessSelectorRadius = 10.dp
    val lightnessOffsetPx = remember {
        mutableFloatStateOf(
            with(density) {
                // the lightness default value is 1, so the selector default position should be on the right side
                //(colorMapWidth - lightnessSelectorRadius).toPx()
                (colorMapWidth - lightnessSelectorRadius).toPx() - (1f - lightness.floatValue)*(colorMapWidth).toPx()
            }
        )
    }

    // ========= color lightness =========


    // ========= color hue =========
    val selectedColor = remember {
        mutableStateOf(color)
    }
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = colorsState.backgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                // color map and selector

                ColorMapSelector(
                    density = density,
                    paddingHorizontal = paddingHorizontal,
                    colorMapWidth = colorMapWidth,
                    colorMapSelectorRadius = colorMapSelectionRadius,
                    colorMapOffsetPx = colorMapOffsetPx.floatValue,
                    onColorMapOffsetPx = {
                        colorMapOffsetPx.floatValue = it
                    },
                    saturation = saturation.floatValue,
                    lightness = lightness.floatValue,
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                Text(
                    text = stringResource(R.string.saturation),
                    color = colorsState.textColor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                // saturation
                SLSelector(
                    density = density,
                    paddingHorizontal = paddingHorizontal,
                    slWidth = colorMapWidth,
                    slSelectorRadius = saturationSelectorRadius,
                    slOffsetPx = saturationOffsetPx.floatValue,
                    onSLOffsetPx = {
                        saturationOffsetPx.floatValue = it
                        val correctOffset = calculateCorrectOffset(
                            selectorOffset = saturationOffsetPx.floatValue,
                            selectorRadius = with(density) {
                                saturationSelectorRadius.toPx()
                            }
                        )
                        saturation.floatValue = correctOffset / (with(density) {
                            colorMapWidth.toPx()
                        })
                    }
                )
                Text(
                    text = stringResource(R.string.lightness), color = colorsState.textColor, fontSize = 14.sp, modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                // lightness
                SLSelector(
                    density = density,
                    paddingHorizontal = paddingHorizontal,
                    slWidth = colorMapWidth,
                    slSelectorRadius = lightnessSelectorRadius,
                    slOffsetPx = lightnessOffsetPx.floatValue,
                    onSLOffsetPx = {
                        lightnessOffsetPx.floatValue = it
                        val correctOffset = calculateCorrectOffset(
                            selectorOffset = lightnessOffsetPx.floatValue,
                            selectorRadius = with(density) {
                                lightnessSelectorRadius.toPx()
                            }
                        )
                        lightness.floatValue = correctOffset / (with(density) {
                            colorMapWidth.toPx()
                        })
                    }
                )


                // selected color result
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                ColorResult(color = getSelectedColor(
                    colorMapOffset = calculateCorrectOffset(
                        selectorOffset = colorMapOffsetPx.floatValue,
                        selectorRadius = with(density) {
                            colorMapSelectionRadius.toPx()
                        }
                    ),
                    colorMapWidth = with(density) {
                        colorMapWidth.toPx()
                    },
                    saturation = saturation.floatValue,
                    lightness = lightness.floatValue,
                ),
                    borderColor = colorsState.borderColor
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        selectedColor.value = getSelectedColor(
                            colorMapOffset = calculateCorrectOffset(
                                selectorOffset = colorMapOffsetPx.floatValue,
                                selectorRadius = with(density) {
                                    colorMapSelectionRadius.toPx()
                                }
                            ),
                            colorMapWidth = with(density) {
                                colorMapWidth.toPx()
                            },
                            saturation = saturation.floatValue,
                            lightness = lightness.floatValue,
                        )
                        onColorSelected(selectedColor.value)
                    },
                        colors = ButtonDefaults.buttonColors(
                            BackgroundCardColor
                        ),
                        border = BorderStroke(0.5.dp, colorsState.borderColor)
                        ) {
                        Text(text = stringResource(R.string.apply), color = colorsState.textColor)
                    }
                }

            }
        }
    }
}

@Composable
private fun SLSelector(
    density: Density,
    paddingHorizontal: Dp,
    slWidth: Dp,
    slSelectorRadius: Dp,
    slOffsetPx: Float,
    onSLOffsetPx: (slOffsetPx: Float) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal)
            .height(slSelectorRadius * 2)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50))
                .background(White)
                .border(2.dp, White, RoundedCornerShape(50))
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val offsetPx = offset.x - with(density) {
                            // the tapped position need to minus the selector's radius as the correct position for the selector
                            slSelectorRadius.toPx()

                        }

                        onSLOffsetPx(offsetPx)
                    }
                }
        )
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(slOffsetPx.roundToInt(), 0)
                }
                .size(slSelectorRadius * 2)
                .clip(CircleShape)
                .background(White)
                .border(2.dp, Black, CircleShape)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val offsetPx = (slOffsetPx + delta).coerceIn(
                            /*
                            keep the drag in the range that the minimum is zero minus selector's radius
                            and the maxiimum is the color map's width minus selector's radius
                             */
                            with(density) {
                                -slSelectorRadius.toPx()
                            },
                            with(density) {
                                (slWidth - slSelectorRadius).toPx()
                            }
                        )
                        onSLOffsetPx(offsetPx)

                    }
                )
        )
    }
}

@Composable
private fun ColorResult(
    color: Color,
    borderColor: Color,
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
                .aspectRatio(1f),
            color = color,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.5.dp, borderColor)
        ) {}

        Spacer(modifier = Modifier.padding(vertical = 6.dp))
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "R: ${color.red * 255}",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = White,
//            )
//            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
//            Text(
//                text = "G: ${color.green * 255}",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = White,
//            )
//            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
//            Text(
//                text = "B: ${color.blue * 255}",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = White,
//            )
//        }
//        Spacer(modifier = Modifier.padding(vertical = 2.dp))
//        Text(
//            text = color.toHexCode(),
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = White,
//        )
    }
}

private fun Color.toHexCode(): String {
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255

    return String.format("#%02x#%02x#%02x", red.toInt(), green.toInt(), blue.toInt())
}

@Composable
private fun ColorMapSelector(
    density: Density,
    paddingHorizontal: Dp,
    colorMapWidth: Dp,
    colorMapSelectorRadius: Dp,
    colorMapOffsetPx: Float,
    onColorMapOffsetPx: (colorMapOffsetPx: Float) -> Unit,
    saturation: Float,
    lightness: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingHorizontal)
            .height(colorMapSelectorRadius * 2)
    ) {
        // color map
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(50))
                .background(
                    createColorMap(
                        with(density) {
                            colorMapWidth.toPx()
                        }
                    )
                )
                .border(2.dp, White, RoundedCornerShape(50))
                .pointerInput(Unit) {
                    // touch color map and move selector
                    detectTapGestures { offset ->

                        val offsetPx = offset.x - with(density) {
                            // the tapped position need to minus the selector's radius
                            // as the correct position for the selector
                            colorMapSelectorRadius.toPx()
                        }
                        onColorMapOffsetPx(offsetPx)
                    }

                }
        )
        Box(modifier = Modifier
            .offset {
                IntOffset(colorMapOffsetPx.roundToInt(), 0)
            }
            .size(colorMapSelectorRadius * 2)
            .clip(CircleShape)
            .background(
                getSelectedColor(
                    colorMapOffset = calculateCorrectOffset(
                        selectorOffset = colorMapOffsetPx,
                        selectorRadius = with(density) {
                            colorMapSelectorRadius.toPx()
                        }
                    ),
                    colorMapWidth = with(density) {
                        colorMapWidth.toPx()
                    },
                    saturation = saturation,
                    lightness = lightness,
                )
            )
            .border(2.dp, Black, CircleShape)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->

                    val offsetPx = (colorMapOffsetPx + delta).coerceIn(
                        /*
                        keep the drag int the range that minimum is zero minus selector's radius
                        and the maximum is the color map's width minus selector's radius
                         */
                        with(density) {
                            -colorMapSelectorRadius.toPx()
                        },
                        with(density) {
                            (colorMapWidth - colorMapSelectorRadius).toPx()
                        }
                    )
                    onColorMapOffsetPx(offsetPx)
                }
            )
        )
    }

}

private fun calculateCorrectOffset(
    selectorOffset: Float,
    selectorRadius: Float,
): Float {
    return selectorOffset + selectorRadius
}

private fun getSelectedColor(
    colorMapOffset: Float, colorMapWidth: Float, saturation: Float, lightness: Float,
): Color {
    val hue = (colorMapOffset / colorMapWidth) * 360f
    return Color(
        HSVToColor(
            floatArrayOf(
                hue,
                saturation,
                lightness,
            )
        )
    )
}

private fun createColorMap(colorMapWidth: Float): Brush {
    val colors = mutableListOf<Color>()

    for (i in 0..360) {
        val saturation = 1f
        val lightness = 1f

        val hsv = HSVToColor(
            floatArrayOf(
                i.toFloat(),
                saturation,
                lightness,
            )
        )
        colors.add(Color(hsv))
    }
    return Brush.horizontalGradient(
        colors = colors,
        startX = 0f,
        endX = colorMapWidth,
    )
}

private fun getSaturationFromColor(color: Color) : Float {
    var hsv = FloatArray(3)
    android.graphics.Color.colorToHSV(color.toArgb(), hsv)
    val saturation = hsv[1]

    return saturation
}

private fun getLightnessFromColor(color: Color) : Float {
    var hsv = FloatArray(3)
    android.graphics.Color.colorToHSV(color.toArgb(), hsv)
    val lightness = hsv[2]

    return lightness
}

private fun getHeuFromColor(color: Color) : Float {
    var hsv = FloatArray(3)
    android.graphics.Color.colorToHSV(color.toArgb(), hsv)
    val heu = hsv[0]

    return heu
}