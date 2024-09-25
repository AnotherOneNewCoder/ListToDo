package ru.zhogin.app.tasks.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("public_tasks")
data class TaskDbo (
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String? = null,
    @ColumnInfo("priority")
    val priority: Int,
    @ColumnInfo("done")
    val done: Boolean,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("done_date")
    val doneDate: Long,
    @ColumnInfo("reminder")
    val reminder: Boolean = false,
    @ColumnInfo("reminder_date")
    val reminderDate: Long = 0L,
    @ColumnInfo("test")
    val test: String = "",

)