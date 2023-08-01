package it.icsone.listfromapicompose.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import it.icsone.listfromapicompose.ui.commons.MyTopAppBar
import it.icsone.listfromapicompose.ui.home.widgets.ErrorView
import it.icsone.listfromapicompose.ui.home.widgets.LoadingView
import it.icsone.listfromapicompose.ui.home.widgets.TodoList
import it.icsone.listfromapicompose.ui.states.TodoListUiState
import it.icsone.listfromapicompose.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(navController: NavController, padding: PaddingValues) {

    val vm = koinViewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = vm.uiState.collectAsState()

    /*LaunchedEffect(Unit) {
        vm.getTodos()
    }*/

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    vm.getTodos()
                }
            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "")
            }
        },
    ) { _ ->
        when (uiState.value) {
            // Loader
            is TodoListUiState.Loading -> LoadingView(padding = padding)

            // Success
            is TodoListUiState.Success -> {
                val list = (uiState.value as TodoListUiState.Success).data
                TodoList(navController = navController, list = list, padding = padding)
            }

            // Error
            is TodoListUiState.Error -> {
                val error = (uiState.value as TodoListUiState.Error).message
                ErrorView(error = error, padding = padding)
            }
        }
    }
}