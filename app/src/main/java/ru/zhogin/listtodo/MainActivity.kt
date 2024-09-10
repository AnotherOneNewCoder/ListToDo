package ru.zhogin.listtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zhogin.app.tasks.presentation.viewmodel.PublicTasksViewModel
import ru.zhogin.app.uikit.ListToDoTheme
import ru.zhogin.listtodo.presentation.ui.bottombar.BottomBar
import ru.zhogin.listtodo.presentation.ui.navigation.NavigationGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListToDoTheme {
                val viewmodel: PublicTasksViewModel = hiltViewModel()
                val state = viewmodel.stateByDate.collectAsState()
                val stateDone = viewmodel.stateByDateDone.collectAsState()
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navHostController = navController,
                            counterNotDone = state.value.tasks.size,
                            counterDone = stateDone.value.tasks.size)
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        paddingValues = innerPadding,
                        state = state.value,
                        newTask = viewmodel.newTask,
                        onEvent = viewmodel::onEvent,
                        stateDone = stateDone.value,
                    )
                }
            }
        }
    }
}

