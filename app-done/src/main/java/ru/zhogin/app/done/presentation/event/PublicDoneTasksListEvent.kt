package ru.zhogin.app.done.presentation.event


import ru.zhogin.app.tasks.presentation.models.TaskUI

sealed interface PublicDoneTasksListEvent {
    data object DismissPublicDoneTasks: PublicDoneTasksListEvent
    data class SelectPublicDoneTask(val task: TaskUI): PublicDoneTasksListEvent
    data object DeletePublicDoneTask: PublicDoneTasksListEvent
    data class OnOptionsRevealedChangedToTrue(val task: TaskUI): PublicDoneTasksListEvent
    data class OnOptionsRevealedChangedToFalse(val task: TaskUI): PublicDoneTasksListEvent
}