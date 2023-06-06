package it.icsone.listfromapicompose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.icsone.listfromapicompose.services.ApiService
import it.icsone.listfromapicompose.ui.states.TodoListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {
    private val _uiState: MutableStateFlow<TodoListUiState> =
        MutableStateFlow(TodoListUiState.Loading)
    val uiState get() = _uiState
    var showDialog: Boolean by mutableStateOf(false)

    init {
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            apiService.getTodos()
                .onStart {
                    _uiState.value = TodoListUiState.Loading
                }
                .catch {
                    _uiState.value =
                        TodoListUiState.Error(it.message ?: "An unexpected error occurred")
                }
                .collect {
                    _uiState.value = TodoListUiState.Success(it)
                }
        }
    }

}