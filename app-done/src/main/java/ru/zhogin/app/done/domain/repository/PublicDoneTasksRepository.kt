package ru.zhogin.app.done.domain.repository

import com.zhogin.common.domain.Task
import kotlinx.coroutines.flow.Flow


interface PublicDoneTasksRepository {
    fun getAllPublicDoneTasksByDate(): Flow<List<Task>>
    suspend fun getPublicDoneTask(id: Long) : Task
    suspend fun deletePublicTask(task: Task)
}