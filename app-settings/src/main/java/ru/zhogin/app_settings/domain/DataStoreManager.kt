package ru.zhogin.app_settings.domain

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app_settings.data.model.SettingsData

interface DataStoreManager {
    suspend fun saveSettings(settingsData: SettingsData)
    fun getSettings(): Flow<SettingsData>
}