package ru.zhogin.app.tasks.presentation.event

import ru.zhogin.app.tasks.presentation.models.TaskUI

sealed interface PublicTasksListEvent {
    data object OnAddNewPublicTaskClick: PublicTasksListEvent
    data object DismissPublicTasks: PublicTasksListEvent
    data class OnTitleChanged(val value: String): PublicTasksListEvent
    data class OnDescriptionChanged(val value: String): PublicTasksListEvent
    data class OnPriorityChanged(val value: Int): PublicTasksListEvent
    data class OnDoneChanged(val value: Boolean): PublicTasksListEvent
    data object SavePublicTask: PublicTasksListEvent
    data class SelectPublicTask(val task: TaskUI): PublicTasksListEvent
    data class EditPublicTask(val task: TaskUI): PublicTasksListEvent
    data object DeletePublicTask: PublicTasksListEvent
    data class ChangeDoneStatusPublicTask(val task: TaskUI): PublicTasksListEvent
    data class OnReminderChangeStatus(val value: Boolean): PublicTasksListEvent
    data class OnReminderDateChanged(val value: Long): PublicTasksListEvent
    data class OnOptionsRevealedChangedToTrue(val task: TaskUI): PublicTasksListEvent
    data class OnOptionsRevealedChangedToFalse(val task: TaskUI): PublicTasksListEvent
}