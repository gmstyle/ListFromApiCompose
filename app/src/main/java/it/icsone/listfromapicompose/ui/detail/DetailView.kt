package it.icsone.listfromapicompose.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import it.icsone.listfromapicompose.ui.commons.MyTopAppBar
import it.icsone.listfromapicompose.ui.home.widgets.LoadingView
import it.icsone.listfromapicompose.ui.states.TodoUiState
import it.icsone.listfromapicompose.viewmodels.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController) {
    val vm: DetailViewModel = koinViewModel()
    val coroutineScope = rememberCoroutineScope()
    val uiState = vm.uiState.collectAsState()
    // recupero l'id passato come parametro
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")

    // recupero l'oggetto dall'API
    LaunchedEffect(key1 = Unit, block = {
        vm.getTodo(id = id!!)
    })

    Scaffold(
        topBar = {
            MyTopAppBar(navController = navController, title = "Detaild $id")
        },
    ) {

        when (uiState.value) {

            // Loader
            is TodoUiState.Loading -> LoadingView(it)

            // Success
            is TodoUiState.Success -> {
                val todo = (uiState.value as TodoUiState.Success).data
                Box(modifier = Modifier.padding(it)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Id: ${todo.id}")
                        Text(text = "User Id: ${todo.userId}")
                        Text(text = "Title: ${todo.title}")
                        Text(text = "Completed: ${todo.completed}")
                    }
                }
            }

            // Error
            is TodoUiState.Error -> Text(text = (uiState.value as TodoUiState.Error).message)

        }


    }
}