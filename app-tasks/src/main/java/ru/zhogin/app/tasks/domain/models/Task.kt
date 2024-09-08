package ru.zhogin.app.tasks.domain.models


data class Task(
    val id: Long,
    val title: String,
    val description: String?,
    val priority: Int,
    val done: Boolean,
    val date: Long,
    val doneDate: Long,
)

