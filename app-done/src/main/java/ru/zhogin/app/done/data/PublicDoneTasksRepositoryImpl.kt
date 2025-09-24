package ru.zhogin.app.done.data

import com.zhogin.common.data.db.PublicTasksDatabase
import com.zhogin.common.data.mapper.toTask
import com.zhogin.common.data.mapper.toTaskDbo
import com.zhogin.common.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository

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

    override suspend fun deletePublicTask(task: Task) = database.taskDao.deletePublicTask(task.toTaskDbo())
}