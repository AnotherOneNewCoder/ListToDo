package ru.zhogin.app.tasks.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zhogin.app.tasks.common.toTask
import ru.zhogin.app.tasks.common.toTaskUi
import ru.zhogin.app.tasks.domain.usecases.DeletePublicTaskUseCase
import ru.zhogin.app.tasks.domain.usecases.GetAllPublicDoneTasksByDateUseCase
import ru.zhogin.app.tasks.domain.usecases.GetAllPublicNotDoneTasksByDateUseCase
import ru.zhogin.app.tasks.domain.usecases.GetAllPublicTasksByPriorityUseCase
import ru.zhogin.app.tasks.domain.usecases.InsertPublicTaskUseCase
import ru.zhogin.app.tasks.presentation.event.PublicTasksListEvent
import ru.zhogin.app.tasks.presentation.models.TaskUI
import ru.zhogin.app.tasks.presentation.state.PublicTasksListState
import javax.inject.Inject

@HiltViewModel
class PublicTasksViewModel @Inject constructor(
    getAllTasksByDate: GetAllPublicNotDoneTasksByDateUseCase,
    getAllDoneTasksByDate: GetAllPublicDoneTasksByDateUseCase,
    private val getAllTasksByPriority: GetAllPublicTasksByPriorityUseCase,
    private val insertPublicTask: InsertPublicTaskUseCase,
    private val deletePublicTask: DeletePublicTaskUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PublicTasksListState())
    val stateByDate = combine(
        _state,
        getAllTasksByDate()
    ) { state, tasks ->
        state.copy(
            tasks = tasks.map { it.toTaskUi() }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PublicTasksListState())

    private val _stateDone = MutableStateFlow(PublicTasksListState())
    val stateByDateDone = combine(
        _stateDone,
        getAllDoneTasksByDate()
    ) { state, tasks ->
        state.copy(
            tasks = tasks.map { it.toTaskUi() }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PublicTasksListState())


    var newTask: TaskUI? by mutableStateOf(null)

    fun onEvent(event: PublicTasksListEvent) {
        when (event) {
            PublicTasksListEvent.DeletePublicTask -> {
                viewModelScope.launch {
                    _state.value.selectedTask?.id?.let { id ->
                        _state.update {
                            it.copy(
                                isSelectedTaskSheetOpen = false
                            )
                        }
                        deletePublicTask(id)
                        delay(500L) // animation delay
                        _state.update {
                            it.copy(
                                selectedTask = null
                            )
                        }
                    }
                }
            }

            PublicTasksListEvent.DismissPublicTasks -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isSelectedTaskSheetOpen = false,
                            isAddTaskSheetOpen = false,
                            validationTitleError = null
                        )
                    }
                    delay(500L)
                    newTask = null
                    _state.update {
                        it.copy(selectedTask = null)
                    }
                }
            }

            is PublicTasksListEvent.EditPublicTask -> {
                _state.update {
                    it.copy(
                        selectedTask = null,
                        isAddTaskSheetOpen = true,
                        isSelectedTaskSheetOpen = false
                    )
                }
                newTask = event.task
            }

            PublicTasksListEvent.OnAddNewPublicTaskClick -> {
                _state.update {
                    it.copy(
                        isAddTaskSheetOpen = true
                    )
                }
                newTask = emptyTask
            }

            is PublicTasksListEvent.OnDescriptionChanged -> {
                newTask = newTask?.copy(
                    description = event.value
                )
            }

            is PublicTasksListEvent.OnDoneChanged -> {
                newTask = newTask?.copy(
                    done = event.value
                )
            }

            is PublicTasksListEvent.OnPriorityChanged -> {
                newTask = newTask?.copy(
                    priority = event.value
                )
            }

            is PublicTasksListEvent.OnTitleChanged -> {
                newTask = newTask?.copy(
                    title = event.value
                )
            }

            PublicTasksListEvent.SavePublicTask -> {
                newTask?.let { task ->

                    if (task.title.isNotBlank()) {
                        _state.update {
                            it.copy(
                                isAddTaskSheetOpen = false,
                                validationTitleError = null,
                            )
                        }
                        viewModelScope.launch {
                            newTask = newTask?.copy(
                                date = System.currentTimeMillis()
                            )
                            newTask?.toTask()?.let { insertPublicTask(it) }
                            delay(500L)
                            newTask = null
                        }

                    } else {
                        _state.update {
                            it.copy(
                                validationTitleError = "Title should be filled!"
                            )
                        }
                    }
                }
            }

            is PublicTasksListEvent.SelectPublicTask -> {
                _state.update {
                    it.copy(
                        selectedTask = event.task,
                        isSelectedTaskSheetOpen = true,
                    )
                }
            }

            is PublicTasksListEvent.ChangeDoneStatusPublicTask ->
                viewModelScope.launch {
                    val task = event.task.copy(
                        done = !event.task.done,
                        doneDate = System.currentTimeMillis(),
                    )
                    insertPublicTask(task.toTask())
                    delay(500L)
                    newTask = null

                    _state.update {
                        it.copy(
                            isSelectedTaskSheetOpen = false,
                            isAddTaskSheetOpen = false,
                            validationTitleError = null
                        )
                    }
                }
        }
    }
}

private val emptyTask = TaskUI(
    id = 0,
    title = "",
    description = null,
    priority = 0,
    done = false,
    date = 0L,
    doneDate = 0L,
)