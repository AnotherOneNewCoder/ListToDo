package ru.zhogin.app.tasks.presentation.alarm

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import ru.zhogin.app.tasks.R
import ru.zhogin.app.tasks.common.toTask
import ru.zhogin.app.tasks.domain.usecases.UpdatePublicTaskUseCase
import ru.zhogin.app.tasks.presentation.models.TaskUI
import javax.inject.Inject

const val DONE = "DONE"
const val REJECT = "REJECT"
const val CHANNEL = "channel"

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var updatePublicTaskUseCase: UpdatePublicTaskUseCase
    private lateinit var mediaPlayer: MediaPlayer

    override fun onReceive(context: Context, intent: Intent) {
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_signal)

        val taskGson = intent.getStringExtra(TASK)
        val task = Gson().fromJson(taskGson, TaskUI::class.java)

        val doneIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(TASK, taskGson)
            action = DONE
        }
        val donePendingIntent = PendingIntent.getBroadcast(
            context, task.id.toInt(), doneIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val closeIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(TASK, taskGson)
            action = REJECT
        }
        val closePendingIntent = PendingIntent.getBroadcast(
            context, task.id.toInt(), closeIntent, PendingIntent.FLAG_IMMUTABLE
        )

        when (intent.action) {
            DONE -> {
                cancelAlarm(context, task)
                runBlocking {
                    updatePublicTaskUseCase.invoke(task.copy(done = true, reminder = false).toTask())
                }

            }
            REJECT -> {
                cancelAlarm(context, task)
                runBlocking {
                    updatePublicTaskUseCase.invoke(task.copy(reminder = false).toTask())
                }
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                        context,
                        POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED) {
                        val notification = NotificationCompat.Builder(context, CHANNEL)
                            .setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle("Task Reminder")
                            .setContentText(task.title)
                            .addAction(R.drawable.ic_check, DONE, donePendingIntent)
                            .addAction(R.drawable.ic_close, "CLOSE", closePendingIntent)
                            .build()
                        NotificationManagerCompat.from(context).notify(1, notification)
                    }
                } else {
                    val notification = NotificationCompat.Builder(context, CHANNEL)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Task Reminder")
                        .setContentText(task.title)
                        .addAction(R.drawable.ic_check, DONE, donePendingIntent)
                        .addAction(R.drawable.ic_close, "CLOSE", closePendingIntent)
                        .build()
                    NotificationManagerCompat.from(context).notify(1, notification)
                }
                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.release()
                }
                mediaPlayer.start()
            }
        }
    }
}