package it.icsone.listfromapicompose.ui.states

import it.icsone.listfromapicompose.models.Todo

sealed class TodoUiState {
    data class Success(val data: Todo) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
    data object Loading : TodoUiState()
}
