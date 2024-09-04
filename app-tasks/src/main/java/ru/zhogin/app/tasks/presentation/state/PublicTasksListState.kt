package ru.zhogin.app.tasks.presentation.state

import ru.zhogin.app.tasks.presentation.models.TaskUI

data class PublicTasksListState(
    val tasks: List<TaskUI> = emptyList(),
    val selectedTask: TaskUI? = null,
    val isAddTaskSheetOpen: Boolean = false,
    val isSelectedTaskSheetOpen: Boolean = false,
    val validationTitleError: String? = null,
)