package ru.zhogin.app.tasks.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.tasks.common.toTask
import ru.zhogin.app.tasks.common.toTaskDbo
import ru.zhogin.app.tasks.data.db.PublicTasksDatabase
import ru.zhogin.app.tasks.domain.models.Task
import ru.zhogin.app.tasks.domain.repository.PublicTasksRepository
import javax.inject.Inject

class PublicTasksRepositoryImpl @Inject constructor(
    private val database: PublicTasksDatabase
) : PublicTasksRepository {

    override fun getAllPublicTasksByDate(): Flow<List<Task>> {
        return database.taskDao::getAllPublicTasksByDate.invoke()
            .map { list ->
                list.filter { !it.done }
                    .map { it.toTask() }
            }
    }

    override fun getAllPublicTasksByPriority(): Flow<List<Task>> {
        return database.taskDao::getAllPublicTasksByPriority.asFlow()
            .map { list ->
                list.filter { !it.done }
                    .map { it.toTask() }
            }
    }

    override suspend fun getPublicTask(id: Long) : Task = database.taskDao.selectPublicTask(id).toTask()

    override suspend fun insertPublicTask(task: Task) = database.taskDao.insertPublicTask(task.toTaskDbo())

    override suspend fun deletePublicTask(id: Long) = database.taskDao.deletePublicTask(id)

}