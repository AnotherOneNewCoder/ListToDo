package ru.zhogin.app.tasks.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.tasks.common.toTask
import ru.zhogin.app.tasks.common.toTaskDbo
import ru.zhogin.app.tasks.data.db.PublicTasksDatabase
import ru.zhogin.app.tasks.domain.models.Task
import javax.inject.Inject

class PublicTasksRepository @Inject constructor(
    private val database: PublicTasksDatabase
) {
    fun getAllPublicTasksByDate(): Flow<List<Task>> {
        return database.taskDao::getAllPublicTasksByDate.asFlow()
            .map { list ->
                list.filter { !it.done }
                    .map { it.toTask() }
            }
    }

    fun getAllPublicTasksByPriority(): Flow<List<Task>> {
        return database.taskDao::getAllPublicTasksByPriority.asFlow()
            .map { list ->
                list.filter { !it.done }
                    .map { it.toTask() }
            }
    }

    suspend fun getPublicTask(id: Long) : Task = database.taskDao.selectPublicTask(id).toTask()

    suspend fun insertPublicTask(task: Task) = database.taskDao.insertPublicTask(task.toTaskDbo())

    suspend fun deletePublicTask(id: Long) = database.taskDao.deletePublicTask(id)

}