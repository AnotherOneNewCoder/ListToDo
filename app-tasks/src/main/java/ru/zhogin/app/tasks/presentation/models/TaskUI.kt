package ru.zhogin.app.tasks.presentation.models

data class TaskUI(
    val id: Long,
    val title: String,
    val description: String?,
    val priority: Int,
    val done: Boolean,
    val date: Long,
) {
}