package ru.zhogin.app.uikit.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
) {
    // State variables for RGBA values
    val alpha = rememberSaveable { mutableFloatStateOf(1f) }
    val red = rememberSaveable { mutableFloatStateOf(0f) }
    val green = rememberSaveable { mutableFloatStateOf(0f) }
    val blue = rememberSaveable { mutableFloatStateOf(0f) }

    // Derived state for the color based on RGBA values
    val color by remember {
        derivedStateOf {
            Color(red.floatValue, green.floatValue, blue.floatValue, alpha.floatValue)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        // Display the current color in a Box with a MaterialTheme shape
        Row {
            Box(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = color, shape = MaterialTheme.shapes.large)
            )
        }
        // Sliders for adjusting RGBA values
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            ColorSlider("A", alpha, color.copy(1f))
            ColorSlider("R", red, Color.Red)
            ColorSlider("G", green, Color.Green)
            ColorSlider("B", blue, Color.Blue)
        }
    }
}

@Composable
internal fun ColorSlider(
    label: String,
    valueState: MutableState<Float>,
    color: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(text = label)
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                activeTrackColor = color
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valueState.value.toColorInt().toString(),
            modifier = Modifier.width(25.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**
 * Converts a float value in the range [0, 1] to an integer color component in the range [0, 255].
 *
 * @return The integer representation of the color component.
 */
internal fun Float.toColorInt(): Int = (this * 255 + 0.5f).toInt()