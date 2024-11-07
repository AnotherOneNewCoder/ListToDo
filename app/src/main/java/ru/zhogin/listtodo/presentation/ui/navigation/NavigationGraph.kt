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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app_settings.presentation.event.ColorPickEvent
import ru.zhogin.app_settings.presentation.picker.ColorPicker2
import ru.zhogin.app_settings.presentation.state.ColorsState
import ru.zhogin.listtodo.R

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

@Composable
internal fun SettingsScreen(
    paddingValues: PaddingValues,
    colorState: ColorsState,
    onColorEvent: (ColorPickEvent) -> Unit,
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    when (colorState.isColorPickerSheetOpen) {
        true -> {
            ColorPicker2(
                color = colorState.selectedColor?.color ?: BackgroundColor,
                onColorSelected = {
                    when(count) {
                        1 -> {
                            onColorEvent(ColorPickEvent.ChangeBackgroundColor(it))
                        }
                        2 -> {
                            onColorEvent(ColorPickEvent.ChangeBackgroundCardColor(it))
                        }
                        3 -> {
                            onColorEvent(ColorPickEvent.ChangeBorderColor(it))
                        }
                        4 -> {
                            onColorEvent(ColorPickEvent.ChangeTextColor(it))
                        }
                        5 -> {
                            onColorEvent(ColorPickEvent.ChangeHintColor(it))
                        }
                        6 -> {
                            onColorEvent(ColorPickEvent.ChangeBadgeColor(it))
                        }
                        7 -> {
                            onColorEvent(ColorPickEvent.ChangeFirstGradientColor(it))
                        }
                        8 -> {
                            onColorEvent(ColorPickEvent.ChangeSecondGradientColor(it))
                        }
                    }


                },
                onDismissRequest = {
                    onColorEvent(ColorPickEvent.OnCloseColorPicker)
                })


        }

        false -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colorState.backgroundColor)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.background_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.backgroundColor,
            onClick = {
                count = 1
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.card_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.backgroundCardColor,
            onClick = {
                count = 2
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.border_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.borderColor,
            onClick = {
                count = 3
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.text_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.textColor,
            onClick = {
                count = 4
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.hint_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.hintColor,
            onClick = {
                count = 5
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.badge_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.badgeColor,
            onClick = {
                count = 6
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.first_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.firstGradientColor,
            onClick = {
                count = 7
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorRow(
            text = stringResource(R.string.second_gradient_color),
            backgroundCardColor = colorState.backgroundCardColor,
            borderColor = colorState.borderColor,
            textColor = colorState.textColor,
            touchColor = colorState.secondGradientColor,
            onClick = {
                count = 8
                onColorEvent(ColorPickEvent.OnOpenColorPicker)
            }
        )



    }
}

@Composable
internal fun ColorRow(
    text: String,
    backgroundCardColor: Color,
    borderColor: Color,
    textColor: Color,
    touchColor: Color,
    onClick:() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(backgroundCardColor, RoundedCornerShape(12.dp))
            .border(0.5.dp, borderColor, RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = text, color = textColor, modifier = Modifier.padding(start = 16.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 5.dp)
                .width(40.dp)
                .height(40.dp)
                .background(touchColor, RoundedCornerShape(50))
                .border(0.5.dp, borderColor, RoundedCornerShape(50))
                .clickable {
                    onClick()
                }
        )
    }
}

