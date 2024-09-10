package ru.zhogin.app.tasks.common.util

import java.text.SimpleDateFormat
import java.util.Date

object Formater {
    fun convertMillisecondsToTimestamp(milliseconds: Long): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = Date(milliseconds)
        val dateString = sdf.format(date)
        val list = dateString.split('-')
        if (list.size < 2) {
            return "wrong format"
        } else {
            val day = if (list[0].startsWith('0')) list[0].drop(1) else list[0]
            val month: String = when(list[1]) {
                "01" -> "янв"
                "02" -> "фев"
                "03" -> "мар"
                "04" -> "апр"
                "05" -> "мая"
                "06" -> "июня"
                "07" -> "июля"
                "08" -> "авг"
                "09" -> "сен"
                "10" -> "окт"
                "11" -> "ноя"
                "12" -> "дек"
                else -> return "wrong format"
            }
            return "$day $month"
        }
    }
}