package ru.zhogin.listtodo.presentation.ui.navigation

sealed class NavigationScreens(val route: String) {
    data object PublicNotDoneTaskScreen: NavigationScreens("tasks_in_progress")
    data object PublicDoneTaskScreen: NavigationScreens("tasks_done")
    data object SettingsScreen: NavigationScreens("settings_screen")
}