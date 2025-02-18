package ru.zhogin.app_settings.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zhogin.app_settings.data.DataStoreManagerImpl
import ru.zhogin.app_settings.domain.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ) : DataStoreManager = DataStoreManagerImpl(context)
}