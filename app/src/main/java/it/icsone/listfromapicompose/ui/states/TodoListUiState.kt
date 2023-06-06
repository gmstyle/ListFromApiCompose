package it.icsone.listfromapicompose.ui.states

import it.icsone.listfromapicompose.models.Todo

sealed class TodoListUiState() {

    data class Success(val data: List<Todo>) : TodoListUiState()
    data class Error(val message: String) : TodoListUiState()
    object Loading : TodoListUiState()
}
