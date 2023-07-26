package it.icsone.listfromapicompose.ui.home.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.icsone.listfromapicompose.models.Todo
import it.icsone.listfromapicompose.ui.theme.ListFromApiComposeTheme

@Composable
fun TodoList(
    navController: NavController,
    list: List<Todo>,
    padding: PaddingValues
) {
    Column(modifier = Modifier.padding(padding)) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            /*items(
                items = list,
                key = { todo -> todo.id ?: 0 },
            ) { todo ->
                TodoRow(navController = navController, todo = todo)
            }*/
            itemsIndexed(
                items = list,
                key = { _, todo -> todo.id ?: 0 },
            ) { _, todo ->
                TodoRow(navController = navController, todo = todo)
            }
        }
    }
}

@Preview( showBackground = true)
@Composable
fun PreviewTodoList() {
    val list: List<Todo> = listOf(
        Todo(
            userId = 1,
            id = 1,
            title = "delectus aut autem",
            completed = false
        ),
        Todo(
            userId = 1,
            id = 2,
            title = "quis ut nam facilis et officia qui",
            completed = false
        ),
        Todo(
            userId = 1,
            id = 3,
            title = "fugiat veniam minus",
            completed = false
        )
    )
    ListFromApiComposeTheme() {
        val navController = NavController(LocalContext.current)
        TodoList(navController = navController,list, PaddingValues(8.dp))
    }

}
