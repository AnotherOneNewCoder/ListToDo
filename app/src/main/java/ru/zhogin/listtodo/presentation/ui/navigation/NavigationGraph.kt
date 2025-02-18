package ru.zhogin.listtodo.presentation.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.app.done.presentation.event.PublicDoneTasksListEvent
import ru.zhogin.app.done.presentation.state.PublicDoneTasksListState
import ru.zhogin.app.done.presentation.ui.PublicDoneTasksScreen
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import ru.zhogin.app.tasks.presentation.ui.screens.PublicNotDoneTaskScreen
import ru.zhogin.app_settings.presentation.event.ColorPickEvent
import ru.zhogin.app_settings.presentation.state.ColorsState
import ru.zhogin.app_settings.presentation.ui.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    state: PublicTasksListState,
    stateDone: PublicDoneTasksListState,
    newTask: TaskUI?,
    onEvent: (PublicTasksListEvent) -> Unit,
    onEventDone: (PublicDoneTasksListEvent) -> Unit,
    colorState: ColorsState,
    onColorEvent: (ColorPickEvent) -> Unit,
) {
    NavHost(navController = navController,
        startDestination = NavigationScreens.PublicNotDoneTaskScreen.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )

        }) {
        composable(NavigationScreens.PublicNotDoneTaskScreen.route) {
            PublicNotDoneTaskScreen(
                modifier = Modifier.padding(paddingValues),
                state = state,
                newTask = newTask,
                onEvent = onEvent,
                colorState = colorState,
            )
        }
        composable(NavigationScreens.PublicDoneTaskScreen.route) {
            PublicDoneTasksScreen(
                modifier = Modifier.padding(paddingValues),
                state = stateDone,
                onEvent = onEventDone,
                colorState = colorState,
            )


        }
        composable(NavigationScreens.SettingsScreen.route) {
            SettingsScreen(
                paddingValues = paddingValues,
                colorState = colorState,
                onColorEvent = onColorEvent,
            )

        }
    }
}



