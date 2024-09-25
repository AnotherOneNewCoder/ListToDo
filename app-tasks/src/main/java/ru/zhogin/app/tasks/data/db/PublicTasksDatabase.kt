package ru.zhogin.app.tasks.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    version = 3,
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
    )
        .addMigrations(Migration_1_2, Migration_2_3)
        .build()
    return PublicTasksDatabase(publicTasksRoomDatabase)
}

internal val Migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE 'public_tasks' ADD COLUMN 'reminder' INTEGER NOT NULL DEFAULT 0")
        db.execSQL("ALTER TABLE 'public_tasks' ADD COLUMN 'reminder_date' LONG NOT NULL DEFAULT 0")
    }
}
internal val Migration_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE 'public_tasks' ADD COLUMN 'test' TEXT NOT NULL DEFAULT ''")
    }
}