package ru.zhogin.app.done.domain.usecases

import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository
import javax.inject.Inject

class DeletePublicDoneTaskUseCase @Inject constructor(
    private val repository: PublicDoneTasksRepository
) {
    suspend operator fun invoke(id: Long) = repository.deletePublicTask(id)
}
