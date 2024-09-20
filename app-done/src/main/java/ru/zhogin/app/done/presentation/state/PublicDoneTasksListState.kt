package ru.zhogin.app.done.presentation.state

import ru.zhogin.app.tasks.presentation.models.TaskUI

data class PublicDoneTasksListState(
    val tasks: List<TaskUI> = emptyList(),
    val selectedTask: TaskUI? = null,
    val isSelectedTaskSheetOpen: Boolean = false,
)