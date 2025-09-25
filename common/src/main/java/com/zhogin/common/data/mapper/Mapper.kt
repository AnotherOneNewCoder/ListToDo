package com.zhogin.common.data.mapper

import com.zhogin.common.data.db.models.TaskDbo
import com.zhogin.common.domain.Task

fun TaskDbo.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        priority = priority,
        done = done,
        date = date,
        doneDate = doneDate,
        reminder = reminder,
        reminderDate = reminderDate,
    )
}

fun Task.toTaskDbo(): TaskDbo {
    return TaskDbo(
        id = id,
        title = title,
        description = description,
        priority = priority,
        done = done,
        date = date,
        doneDate = doneDate,
        reminder = reminder,
        reminderDate = reminderDate,
    )
}