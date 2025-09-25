package ru.zhogin.app.tasks.domain.repository

import com.zhogin.common.domain.Task
import kotlinx.coroutines.flow.Flow


interface PublicTasksRepository {
    fun getAllPublicNotDoneTasksByDate(): Flow<List<Task>>
    fun getAllPublicTasksByPriority(): Flow<List<Task>>
    suspend fun getPublicTask(id: Long) : Task
    suspend fun insertPublicTask(task: Task)
    suspend fun deletePublicTask(task: Task)
    suspend fun updatePublicTask(task: Task)
}