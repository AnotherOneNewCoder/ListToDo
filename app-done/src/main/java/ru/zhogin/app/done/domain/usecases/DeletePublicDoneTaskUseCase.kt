package ru.zhogin.app.done.domain.usecases

import com.zhogin.common.domain.Task
import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository

import javax.inject.Inject

class DeletePublicDoneTaskUseCase @Inject constructor(
    private val repository: PublicDoneTasksRepository
) {
    suspend operator fun invoke(task: Task) = repository.deletePublicTask(task)
}
