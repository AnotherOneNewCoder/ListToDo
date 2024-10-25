package ru.zhogin.app.tasks.presentation.ui.dialogs

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.tasks.R
import ru.zhogin.app.tasks.presentation.alarm.cancelAlarm
import ru.zhogin.app.tasks.presentation.alarm.setAlarm
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.ReminderView
import ru.zhogin.app.tasks.presentation.ui.components.TaskTextField
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.DarkNavy
import ru.zhogin.app.uikit.GradientBlue
import ru.zhogin.app.uikit.GradientPurple
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.White
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddTaskSheetTest(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    context: Context,
) {
    var isTimePickerVisible by remember {
        mutableStateOf(false)
    }
    var isDatePickerVisible by remember {
        mutableStateOf(false)
    }
    val timePickerState = rememberTimePickerState()
    val datePickerState = rememberDatePickerState()
    val format = remember {
        SimpleDateFormat("HH:mm", Locale.getDefault())
    }
    val formatDate = remember {
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    }

    val resultDateFormater = remember {
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
    }

    var stringFullDate by remember {
        mutableStateOf("")
    }

    var timeInMillis by remember { mutableLongStateOf(newTask?.reminderDate ?: 0L) }

    var dateInMillis by remember { mutableLongStateOf(newTask?.reminderDate ?: 0L) }

    var fullDate by remember {
        mutableLongStateOf(0L)
    }

    if (isTimePickerVisible) {
        Dialog(onDismissRequest = { isTimePickerVisible = false }) {
            Column {
                TimePicker(state = timePickerState)
                Row {
                    Button(onClick = { isTimePickerVisible = false }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                    Button(onClick = {
                        val calendar = Calendar.getInstance().apply {

                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        timeInMillis = calendar.timeInMillis
                        isTimePickerVisible = false
                    }) {
                        Text(text = stringResource(R.string.confirm))
                    }
                }
            }
        }
    }
    if (isDatePickerVisible) {
        DatePickerModal(
            onDateSelected = { dateInMillis = it },
            onDismiss = { isDatePickerVisible = false },
            datePickerState = datePickerState,
        )
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkNavy
            ),
            border = BorderStroke(0.5.dp, Blue)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = onDismissRequest) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Dismiss",
                    tint = Blue
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (newTask?.id == 0L) stringResource(R.string.create_new_task)
                    else stringResource(R.string.edit_task),
                    style = MaterialTheme.typography.Title1.copy(
                        color = White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.title ?: "",
                    placeHolder = stringResource(R.string.title),
                    error = state.validationTitleError,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnTitleChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.description ?: "",
                    placeHolder = stringResource(R.string.description),
                    error = null,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnDescriptionChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                //Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.reminder),
                        color = White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Switch(
                        checked = newTask?.reminder ?: false, onCheckedChange = {
                            onEvent(PublicTasksListEvent.OnReminderChangeStatus(it))
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            uncheckedThumbColor = Blue,
                            uncheckedBorderColor = Blue,
                            checkedBorderColor = Color.Transparent,
                            uncheckedTrackColor = Color.Transparent,
                            checkedTrackColor = Blue

                        )
                    )
                }

                if (newTask?.reminder == true) {

                    stringFullDate =
                        formatDate.format(dateInMillis) + " " + format.format(timeInMillis) + ":00"
                    fullDate = LocalDateTime.parse(stringFullDate, resultDateFormater)
                        .atZone(ZoneId.systemDefault())
                        .toInstant().toEpochMilli()
                    ReminderView(
                        onClickChooseDate = { isDatePickerVisible = true },
                        onClickChooseTime = { isTimePickerVisible = true },
                        date = formatDate.format(dateInMillis),
                        time = format.format(timeInMillis),
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        when (newTask?.reminder) {
                            true -> {
                                if (fullDate > System.currentTimeMillis()) {
                                    onEvent(PublicTasksListEvent.OnReminderDateChanged(fullDate))
                                } else {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.choose_date_in_future),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@Button
                                }
                                cancelAlarm(context, newTask)
                                setAlarm(context, newTask)
                                onEvent(PublicTasksListEvent.SavePublicTask)
                            }

                            false -> {
                                onEvent(PublicTasksListEvent.SavePublicTask)
                            }

                            else -> Toast.makeText(
                                context,
                                context.getString(R.string.unexpected_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                },
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    GradientPurple,
                                    GradientBlue,
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),

                    )
                {
                    Text(
                        text = stringResource(R.string.save),
                        color = White,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}