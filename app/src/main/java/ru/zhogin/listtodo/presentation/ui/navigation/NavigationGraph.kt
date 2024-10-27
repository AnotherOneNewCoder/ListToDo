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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.Navy
import ru.zhogin.app.uikit.Title1

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
            )
        }
        composable(NavigationScreens.PublicDoneTaskScreen.route) {
            PublicDoneTasksScreen(
                modifier = Modifier.padding(paddingValues),
                state = stateDone,
                onEvent = onEventDone,
            )


        }
        composable(NavigationScreens.SettingsScreen.route) {
            LoadingBox(paddingValues = paddingValues)
        }
    }
}


@Composable
internal fun LoadingBox(
    paddingValues: PaddingValues,
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Navy),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.Title1.copy(
                Blue
            ))
            Spacer(modifier = Modifier.height(21.dp))
            CircularProgressIndicator(
                color = Blue
            )
        }

    }
}