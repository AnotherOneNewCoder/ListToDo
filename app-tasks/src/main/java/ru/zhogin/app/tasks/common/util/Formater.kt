package ru.zhogin.app.tasks.common.util

import java.text.SimpleDateFormat
import java.util.Date

object Formater {
    fun convertMillisecondsToTimestamp(milliseconds: Long): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = Date(milliseconds)
        return sdf.format(date)
    }
}