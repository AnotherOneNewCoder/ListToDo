package ru.zhogin.app.tasks.presentation.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import ru.zhogin.app.tasks.presentation.models.TaskUI

const val TASK = "TASK"

internal fun setAlarm(context: Context, task: TaskUI) {

    val intent = Intent(context, AlarmReceiver::class.java).apply {
        putExtra(TASK, Gson().toJson(task))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        task.reminderDate.toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    try {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.reminderDate, pendingIntent)
    } catch (e: SecurityException) {
        e.printStackTrace()
    }

}

internal fun cancelAlarm(context: Context, task: TaskUI) {
    val intent = Intent(context, AlarmReceiver::class.java).apply {
        putExtra(TASK, Gson().toJson(task))
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        task.reminderDate.toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        alarmManager.cancel(pendingIntent)
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}