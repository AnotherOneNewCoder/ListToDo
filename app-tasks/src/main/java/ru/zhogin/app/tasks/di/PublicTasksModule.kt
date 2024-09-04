package ru.zhogin.app.tasks.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zhogin.app.tasks.data.db.PublicTasksDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PublicTasksModule {

    @Provides
    @Singleton
    fun providesPublicTasksDatabase(@ApplicationContext context: Context) : PublicTasksDatabase {
        return PublicTasksDatabase(context)
    }
}