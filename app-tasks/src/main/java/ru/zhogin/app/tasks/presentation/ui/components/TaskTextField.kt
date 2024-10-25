package ru.zhogin.app.tasks.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.GradientPurple

@Composable
internal fun TaskTextField(
    value: String,
    placeHolder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = placeHolder)
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
            ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Blue,
                unfocusedTextColor = Blue,
                cursorColor = Blue,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Blue,
                focusedLabelColor = Blue,
                unfocusedLabelColor = Blue,
                focusedPlaceholderColor = Blue,
                unfocusedPlaceholderColor = Blue,

            )
        )
        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = error,
                    color = GradientPurple
                )
            }

        }
    }
}