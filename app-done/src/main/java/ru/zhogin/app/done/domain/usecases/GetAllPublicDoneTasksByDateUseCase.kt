package ru.zhogin.app.done.domain.usecases

import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository
import javax.inject.Inject

class GetAllPublicDoneTasksByDateUseCase @Inject constructor(
    private val repository: PublicDoneTasksRepository
) {
    operator fun invoke() = repository.getAllPublicDoneTasksByDate()
}