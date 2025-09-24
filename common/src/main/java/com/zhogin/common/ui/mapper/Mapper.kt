package com.zhogin.common.ui.mapper

import com.zhogin.common.domain.Task
import com.zhogin.common.ui.models.TaskUI

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