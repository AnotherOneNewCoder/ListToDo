package ru.zhogin.app.tasks.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.tasks.domain.models.Task

interface PublicTasksRepository {
    fun getAllPublicNotDoneTasksByDate(): Flow<List<Task>>
    fun getAllPublicTasksByPriority(): Flow<List<Task>>
    suspend fun getPublicTask(id: Long) : Task
    suspend fun insertPublicTask(task: Task)
    suspend fun deletePublicTask(task: Task)
    suspend fun updatePublicTask(task: Task)
}