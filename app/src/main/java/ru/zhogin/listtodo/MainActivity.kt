package ru.zhogin.listtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.zhogin.app.tasks.presentation.ui.screens.PublicNotDoneTaskScreen
import ru.zhogin.app.tasks.presentation.viewmodel.PublicTasksViewModel
import ru.zhogin.app.uikit.ListToDoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListToDoTheme {
                val viewmodel: PublicTasksViewModel = hiltViewModel()
                val state = viewmodel.stateByDate.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PublicNotDoneTaskScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state.value,
                        newTask = viewmodel.newTask,
                        onEvent = viewmodel::onEvent
                    )
                }
            }
        }
    }
}

