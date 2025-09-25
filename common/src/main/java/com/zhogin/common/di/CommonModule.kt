package com.zhogin.common.di

import android.content.Context
import com.zhogin.common.data.db.PublicTasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    @Singleton
    fun providesPublicTasksDatabase(@ApplicationContext context: Context) : PublicTasksDatabase {
        return PublicTasksDatabase(context)
    }
}