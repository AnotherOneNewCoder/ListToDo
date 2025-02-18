package ru.zhogin.app_settings.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun ColorRow(
    text: String,
    backgroundCardColor: Color,
    borderColor: Color,
    textColor: Color,
    touchColor: Color,
    onClick:() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(backgroundCardColor, RoundedCornerShape(12.dp))
            .border(0.5.dp, borderColor, RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = text, color = textColor, modifier = Modifier.padding(start = 16.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 5.dp)
                .width(40.dp)
                .height(40.dp)
                .background(touchColor, RoundedCornerShape(50))
                .border(0.5.dp, borderColor, RoundedCornerShape(50))
                .clickable {
                    onClick()
                }
        )
    }
}
