package ru.zhogin.app.tasks.presentation.ui.dialogs

import android.content.Context
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
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.tasks.presentation.alarm.setAlarm
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.components.TaskTextField
import ru.zhogin.app.uikit.Title1
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddTaskSheet(
    onDismissRequest: () -> Unit,
    state: PublicTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    modifier: Modifier = Modifier,
    context: Context,
) {
    var isTimePickerVisible by remember {
        mutableStateOf(false)
    }
    val timePickerState = rememberTimePickerState()
    val format = remember {
        SimpleDateFormat("hh:mm a", Locale.getDefault())
    }
    var timeInMillis by remember { mutableLongStateOf(0L) }

    if (isTimePickerVisible) {
        Dialog(onDismissRequest = { isTimePickerVisible = false }) {
            Column {
                TimePicker(state = timePickerState)
                Row {
                    Button(onClick = { isTimePickerVisible = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        val calendar = Calendar.getInstance().apply {
                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        timeInMillis = calendar.timeInMillis
                        isTimePickerVisible= false
                        onEvent(PublicTasksListEvent.OnReminderDateChanged(timeInMillis))
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
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
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = onDismissRequest) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Dismiss",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Create new task: ",
                    style = MaterialTheme.typography.Title1
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.title ?: "",
                    placeHolder = "Title",
                    error = state.validationTitleError,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnTitleChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskTextField(
                    value = newTask?.description ?: "",
                    placeHolder = "Description",
                    error = null,
                    onValueChanged = { onEvent(PublicTasksListEvent.OnDescriptionChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Reminder:")
                    Spacer(modifier = Modifier.width(12.dp))
                    Switch(checked = newTask?.reminder ?: false, onCheckedChange = {
                        onEvent(PublicTasksListEvent.OnReminderChangeStatus(it))
                    })
                }
                
                if (newTask?.reminder == true) {
                    Text(text = format.format(timeInMillis))
                    IconButton(onClick = { isTimePickerVisible = true }) {
                        Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "Choose date")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (newTask?.reminder == true && newTask.reminderDate != 0L) {
                        setAlarm(context, newTask)
                    }
                    onEvent(PublicTasksListEvent.SavePublicTask)
                })
                {
                    Text(text = "Save")
                }
                Spacer(modifier = Modifier.height(16.dp))

            }

        }
    }
}