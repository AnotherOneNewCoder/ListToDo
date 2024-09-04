package ru.zhogin.app.tasks.domain.usecases

import ru.zhogin.app.tasks.domain.repository.PublicTasksRepository
import javax.inject.Inject

class GetAllPublicTasksByDateUseCase @Inject constructor(
    private val repository: PublicTasksRepository
) {
    operator fun invoke() = repository.getAllPublicTasksByDate()
}