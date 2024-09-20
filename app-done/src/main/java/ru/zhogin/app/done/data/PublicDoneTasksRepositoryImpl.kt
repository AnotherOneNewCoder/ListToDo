package ru.zhogin.app.done.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository
import ru.zhogin.app.tasks.common.toTask
import ru.zhogin.app.tasks.data.db.PublicTasksDatabase
import ru.zhogin.app.tasks.domain.models.Task
import javax.inject.Inject

class PublicDoneTasksRepositoryImpl @Inject constructor(
    private val database: PublicTasksDatabase
) : PublicDoneTasksRepository {
    override fun getAllPublicDoneTasksByDate(): Flow<List<Task>> {
        return database.taskDao::getAllPublicDoneTasksByDate.invoke()
            .map { list ->
                list
                    .map { it.toTask() }
            }
    }

    override suspend fun getPublicDoneTask(id: Long): Task = database.taskDao.selectPublicTask(id).toTask()

    override suspend fun deletePublicTask(id: Long) = database.taskDao.deletePublicTask(id)
}