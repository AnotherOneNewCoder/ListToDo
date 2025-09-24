package ru.zhogin.app.tasks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zhogin.app.tasks.domain.repository.PublicTasksRepository
import ru.zhogin.app.tasks.data.repository.PublicTasksRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PublicTasksModule {

    @Provides
    @Singleton
    fun providesPublicTaskRepository(impl: PublicTasksRepositoryImpl) : PublicTasksRepository = impl
}