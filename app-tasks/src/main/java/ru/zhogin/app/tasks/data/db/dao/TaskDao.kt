package ru.zhogin.app.tasks.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.tasks.data.db.models.TaskDbo

@Dao
interface TaskDao {
    @Query("SELECT * FROM public_tasks ORDER BY priority DESC")
    suspend fun getAllPublicTasksByPriority() : List<TaskDbo>

//    @Query("SELECT * FROM public_tasks ORDER BY date DESC")
//    suspend fun getAllPublicTasksByDate() : List<TaskDbo>

    @Query("SELECT * FROM public_tasks WHERE done = 0 ORDER BY date DESC")
    fun getAllPublicNotDoneTasksByDate() : Flow<List<TaskDbo>>

    @Query("SELECT * FROM public_tasks WHERE done = 1 ORDER BY date DESC")
    fun getAllPublicDoneTasksByDate() : Flow<List<TaskDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicTask(taskDbo: TaskDbo)

    @Update
    suspend fun updateTask(taskDbo: TaskDbo)

    @Delete
    suspend fun deletePublicTask(taskDbo: TaskDbo)

    @Query("SELECT * FROM public_tasks WHERE id = :id")
    suspend fun selectPublicTask(id: Long) : TaskDbo
}