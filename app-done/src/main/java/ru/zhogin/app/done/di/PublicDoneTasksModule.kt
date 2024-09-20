package ru.zhogin.app.done.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zhogin.app.done.data.PublicDoneTasksRepositoryImpl
import ru.zhogin.app.done.domain.repository.PublicDoneTasksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PublicDoneTasksModule {
    @Provides
    @Singleton
    fun providesPublicDoneTaskRepository(impl: PublicDoneTasksRepositoryImpl) : PublicDoneTasksRepository = impl
}