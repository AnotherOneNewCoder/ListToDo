package ru.zhogin.listtodo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zhogin.app.done.presentation.viewmodel.PublicDoneTasksViewModel
import ru.zhogin.app.tasks.presentation.viewmodel.PublicTasksViewModel
import ru.zhogin.app.uikit.BackgroundColor
import ru.zhogin.app.uikit.ListToDoTheme
import ru.zhogin.app_settings.presentation.viewmodel.ColorViewModel
import ru.zhogin.listtodo.presentation.ui.bottombar.BottomBar
import ru.zhogin.listtodo.presentation.ui.navigation.NavigationGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            var statusBarColor by remember {
                mutableStateOf(BackgroundColor)
            }
            ListToDoTheme(
                color = statusBarColor
            ) {


                val viewmodel: PublicTasksViewModel = hiltViewModel()
                val state = viewmodel.stateByDate.collectAsState()

                val colorViewModel: ColorViewModel = hiltViewModel()
                val colorState = colorViewModel.state.collectAsState()

                val viewmodelDone : PublicDoneTasksViewModel = hiltViewModel()
                val stateDone = viewmodelDone.state.collectAsState()
                val navController = rememberNavController()

                LaunchedEffect(
                    key1 = colorState.value.backgroundColor
                ) {
                    statusBarColor = colorState.value.backgroundColor
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navHostController = navController,
                            counterNotDone = state.value.tasks.size,
                            counterDone = stateDone.value.tasks.size,
                            colorState = colorState.value,
                        )
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        paddingValues = innerPadding,
                        state = state.value,
                        newTask = viewmodel.newTask,
                        onEvent = viewmodel::onEvent,
                        stateDone = stateDone.value,
                        onEventDone = viewmodelDone::onEvent,
                        colorState = colorState.value,
                        onColorEvent = colorViewModel::onEvent
                    )
                }
            }
        }
    }
}

