package ru.zhogin.app.tasks.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.zhogin.app.tasks.data.db.dao.TaskDao
import ru.zhogin.app.tasks.data.db.models.TaskDbo

class PublicTasksDatabase internal constructor(private val database: PublicTasksRoomDatabase) {
    val taskDao: TaskDao
        get() = database.taskDao()
}

@Database(
    entities = [
        TaskDbo::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class PublicTasksRoomDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}
fun PublicTasksDatabase(applicationContext: Context) : PublicTasksDatabase {
    val publicTasksRoomDatabase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        PublicTasksRoomDatabase::class.java,
        "public_tasks_db"
    ).build()
    return PublicTasksDatabase(publicTasksRoomDatabase)
}