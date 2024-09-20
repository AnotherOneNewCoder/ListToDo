package ru.zhogin.app.done.presentation.viewmodel

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
import ru.zhogin.app.done.domain.usecases.DeletePublicDoneTaskUseCase
import ru.zhogin.app.done.domain.usecases.GetAllPublicDoneTasksByDateUseCase
import ru.zhogin.app.done.presentation.event.PublicDoneTasksListEvent
import ru.zhogin.app.done.presentation.state.PublicDoneTasksListState
import ru.zhogin.app.tasks.common.toTaskUi
import javax.inject.Inject

@HiltViewModel
class PublicDoneTasksViewModel @Inject constructor(
    getAllPublicDoneTasksByDateUseCase: GetAllPublicDoneTasksByDateUseCase,
    private val deletePublicDoneTaskUseCase: DeletePublicDoneTaskUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PublicDoneTasksListState())
    val state = combine(
        _state,
        getAllPublicDoneTasksByDateUseCase()
    ) { state, tasks ->
        state.copy(
            tasks = tasks.map { it.toTaskUi() }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PublicDoneTasksListState())

    fun onEvent(event: PublicDoneTasksListEvent) {
        when (event) {
            PublicDoneTasksListEvent.DeletePublicDoneTask -> {
                viewModelScope.launch {
                    _state.value.selectedTask?.id?.let { id ->
                        _state.update {
                            it.copy(
                                isSelectedTaskSheetOpen = false
                            )
                        }
                        deletePublicDoneTaskUseCase(id)
                        delay(500L) // animation delay
                        _state.update {
                            it.copy(
                                selectedTask = null
                            )
                        }
                    }
                }
            }
            PublicDoneTasksListEvent.DismissPublicDoneTasks -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isSelectedTaskSheetOpen = false,
                        )
                    }
                    delay(500L)
                    //newTask = null
                    _state.update {
                        it.copy(selectedTask = null)
                    }
                }
            }
            is PublicDoneTasksListEvent.SelectPublicDoneTask -> {
                _state.update {
                    it.copy(
                        selectedTask = event.task,
                        isSelectedTaskSheetOpen = true,
                    )
                }
            }
        }
    }
}