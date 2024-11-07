package ru.zhogin.app_settings.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.zhogin.app_settings.data.model.SettingsData

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
class DataStoreManager(val context: Context) {

    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("background_color")] = settingsData.backgroundColor
            pref[stringPreferencesKey("card_color")] = settingsData.backgroundCardColor
            pref[stringPreferencesKey("border_color")] = settingsData.borderColor
            pref[stringPreferencesKey("text_color")] = settingsData.textColor
            pref[stringPreferencesKey("hint_color")] = settingsData.hintColor
            pref[stringPreferencesKey("badge_color")] = settingsData.badgeColor
            pref[stringPreferencesKey("first_gradient_color")] = settingsData.firstGradientColor
            pref[stringPreferencesKey("second_gradient_color")] = settingsData.secondGradientColor
        }
    }
    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
            backgroundColor = pref[stringPreferencesKey("background_color")] ?: "",
            backgroundCardColor = pref[stringPreferencesKey("card_color")] ?: "",
            borderColor = pref[stringPreferencesKey("border_color")] ?: "",
            textColor = pref[stringPreferencesKey("text_color")] ?: "",
            hintColor = pref[stringPreferencesKey("hint_color")] ?: "",
            badgeColor = pref[stringPreferencesKey("badge_color")] ?: "",
            firstGradientColor = pref[stringPreferencesKey("first_gradient_color")] ?: "",
            secondGradientColor = pref[stringPreferencesKey("second_gradient_color")] ?: "",

        )
    }
}