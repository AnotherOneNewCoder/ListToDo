package ru.zhogin.app.tasks.common

import ru.zhogin.app.tasks.data.db.models.TaskDbo
import ru.zhogin.app.tasks.domain.models.Task
import ru.zhogin.app.tasks.presentation.models.TaskUI

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

fun Task.toTaskUi(): TaskUI {
    return TaskUI(
        id = id,
        title = title,
        description = description,
        priority = priority,
        done = done,
        date = date,
        doneDate = doneDate,
        reminder = reminder,
        reminderDate = reminderDate,
        isOptionsRevealed = false,
    )
}

fun TaskUI.toTask(): Task {
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