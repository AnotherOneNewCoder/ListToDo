package ru.zhogin.app.tasks.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.zhogin.app.tasks.data.db.models.TaskDbo

@Dao
interface TaskDao {
    @Query("SELECT * FROM public_tasks ORDER BY priority DESC")
    suspend fun getAllPublicTasksByPriority() : List<TaskDbo>

    @Query("SELECT * FROM public_tasks ORDER BY date DESC")
    suspend fun getAllPublicTasksByDate() : List<TaskDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicTask(taskDbo: TaskDbo)

    @Query("DELETE FROM public_tasks WHERE id = :id")
    suspend fun deletePublicTask(id: Long)

    @Query("SELECT * FROM public_tasks WHERE id = :id")
    suspend fun selectPublicTask(id: Long) : TaskDbo
}