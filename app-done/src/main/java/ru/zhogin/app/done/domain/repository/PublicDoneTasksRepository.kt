package ru.zhogin.app.done.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.tasks.domain.models.Task

interface PublicDoneTasksRepository {
    fun getAllPublicDoneTasksByDate(): Flow<List<Task>>
    suspend fun getPublicDoneTask(id: Long) : Task
    suspend fun deletePublicTask(id: Long)
}