package ru.zhogin.app_settings.data

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.zhogin.app.uikit.BackgroundCardColor
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app.uikit.BadgeColor
import ru.zhogin.app.uikit.BorderColor
import ru.zhogin.app.uikit.FirstGradientColor
import ru.zhogin.app.uikit.FourthGradientColor
import ru.zhogin.app.uikit.HintColor
import ru.zhogin.app.uikit.SecondGradientColor
import ru.zhogin.app.uikit.TextColor
import ru.zhogin.app.uikit.ThirdGradientColor
import ru.zhogin.app_settings.core.Constants.BACKGROUND_COLOR
import ru.zhogin.app_settings.core.Constants.BADGE_COLOR
import ru.zhogin.app_settings.core.Constants.BORDER_COLOR
import ru.zhogin.app_settings.core.Constants.CARD_COLOR
import ru.zhogin.app_settings.core.Constants.FIRST_GRADIENT_COLOR
import ru.zhogin.app_settings.core.Constants.FOURTH_GRADIENT_COLOR
import ru.zhogin.app_settings.core.Constants.HINT_COLOR
import ru.zhogin.app_settings.core.Constants.SECOND_GRADIENT_COLOR
import ru.zhogin.app_settings.core.Constants.TEXT_COLOR
import ru.zhogin.app_settings.core.Constants.THIRD_GRADIENT_COLOR
import ru.zhogin.app_settings.data.model.SettingsData
import ru.zhogin.app_settings.domain.DataStoreManager
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
class DataStoreManagerImpl @Inject constructor(
    private val context: Context
): DataStoreManager {

    override suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey(BACKGROUND_COLOR)] = settingsData.backgroundColor
            pref[intPreferencesKey(CARD_COLOR)] = settingsData.backgroundCardColor
            pref[intPreferencesKey(BORDER_COLOR)] = settingsData.borderColor
            pref[intPreferencesKey(TEXT_COLOR)] = settingsData.textColor
            pref[intPreferencesKey(HINT_COLOR)] = settingsData.hintColor
            pref[intPreferencesKey(BADGE_COLOR)] = settingsData.badgeColor
            pref[intPreferencesKey(FIRST_GRADIENT_COLOR)] = settingsData.firstGradientColor
            pref[intPreferencesKey(SECOND_GRADIENT_COLOR)] = settingsData.secondGradientColor
            pref[intPreferencesKey(THIRD_GRADIENT_COLOR)] = settingsData.thirdGradientColor
            pref[intPreferencesKey(FOURTH_GRADIENT_COLOR)] = settingsData.fourthGradientColor
        }
    }
    override fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
            backgroundColor = pref[intPreferencesKey(BACKGROUND_COLOR)] ?: BackgroundColor.toArgb(),
            backgroundCardColor = pref[intPreferencesKey(CARD_COLOR)] ?: BackgroundCardColor.toArgb(),
            borderColor = pref[intPreferencesKey(BORDER_COLOR)] ?: BorderColor.toArgb(),
            textColor = pref[intPreferencesKey(TEXT_COLOR)] ?: TextColor.toArgb(),
            hintColor = pref[intPreferencesKey(HINT_COLOR)] ?: HintColor.toArgb(),
            badgeColor = pref[intPreferencesKey(BADGE_COLOR)] ?: BadgeColor.toArgb(),
            firstGradientColor = pref[intPreferencesKey(FIRST_GRADIENT_COLOR)] ?: FirstGradientColor.toArgb(),
            secondGradientColor = pref[intPreferencesKey(SECOND_GRADIENT_COLOR)] ?: SecondGradientColor.toArgb(),
            thirdGradientColor = pref[intPreferencesKey(THIRD_GRADIENT_COLOR)] ?: ThirdGradientColor.toArgb(),
            fourthGradientColor = pref[intPreferencesKey(FOURTH_GRADIENT_COLOR)] ?: FourthGradientColor.toArgb(),

//
//            backgroundColor = pref[intPreferencesKey(BACKGROUND_COLOR)] ?: 0xFF172157,
//            backgroundCardColor = pref[intPreferencesKey(CARD_COLOR)] ?: 0xFF0E132E,
//            borderColor = pref[intPreferencesKey(BORDER_COLOR)] ?: 0xFF40A4FF,
//            textColor = pref[intPreferencesKey(TEXT_COLOR)] ?: 0xFFFFFFFF,
//            hintColor = pref[intPreferencesKey(HINT_COLOR)] ?: 0xFF40A4FF,
//            badgeColor = pref[intPreferencesKey(BADGE_COLOR)] ?: 0xFF580808,
//            firstGradientColor = pref[intPreferencesKey(FIRST_GRADIENT_COLOR)] ?: 0xFF58A6FF,
//            secondGradientColor = pref[intPreferencesKey(SECOND_GRADIENT_COLOR)] ?: 0xFFEE82EE,

        )
    }
}