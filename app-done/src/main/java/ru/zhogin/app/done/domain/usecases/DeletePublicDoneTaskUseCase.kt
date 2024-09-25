package ru.zhogin.app.done.domain.usecases

import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository
import ru.zhogin.app.tasks.domain.models.Task
import javax.inject.Inject

class DeletePublicDoneTaskUseCase @Inject constructor(
    private val repository: PublicDoneTasksRepository
) {
    suspend operator fun invoke(task: Task) = repository.deletePublicTask(task)
}
