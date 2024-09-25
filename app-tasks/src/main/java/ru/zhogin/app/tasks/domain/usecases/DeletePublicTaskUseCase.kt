package ru.zhogin.app.tasks.domain.usecases

import ru.zhogin.app.tasks.domain.models.Task
import ru.zhogin.app.tasks.domain.repository.PublicTasksRepository
import javax.inject.Inject

class DeletePublicTaskUseCase @Inject constructor(
    private val repository: PublicTasksRepository
) {
    suspend operator fun invoke(task: Task) = repository.deletePublicTask(task)
}
