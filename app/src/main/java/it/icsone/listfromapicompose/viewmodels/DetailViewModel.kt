package it.icsone.listfromapicompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.icsone.listfromapicompose.services.ApiService
import it.icsone.listfromapicompose.ui.states.TodoListUiState
import it.icsone.listfromapicompose.ui.states.TodoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val apiService: ApiService) : ViewModel() {
    private val _uiState: MutableStateFlow<TodoUiState> =
        MutableStateFlow(TodoUiState.Loading)
    val uiState get() = _uiState

    fun getTodo(id: Int) {
        viewModelScope.launch {
            apiService.getTodoById(id)
                .collect {
                    _uiState.value = TodoUiState.Success(it)
                }
        }
    }

}