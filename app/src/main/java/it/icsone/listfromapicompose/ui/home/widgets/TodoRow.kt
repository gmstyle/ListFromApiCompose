package it.icsone.listfromapicompose.ui.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.icsone.listfromapicompose.models.Todo
import it.icsone.listfromapicompose.router.Route
import it.icsone.listfromapicompose.ui.theme.ListFromApiComposeTheme

@Composable
fun TodoRow(navController: NavController, todo: Todo) {
    var showDialog: Boolean by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .clickable {
                //showDialog = true
                //vm.selectedTodo = todo

                // passo l'id alla DetailScreen
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("id", todo.id)
                }
                // navigo verso la DetailScreen
                navController.navigate(Route.Detail.route)
            }
            .padding(8.dp),
    ) {

        /*if (showDialog) {
            DetailAlertDialog(todo = todo, onConfirm = { showDialog = false })
        }*/

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(text = todo.id.toString())
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        todo.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Checkbox(checked = todo.completed ?: false, onCheckedChange = null)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTodoRow() {
    val navController = NavController(LocalContext.current)
    ListFromApiComposeTheme() {
        TodoRow(
            navController,
            Todo(
                userId = 1,
                id = 1,
                title = "delectus aut autem",
                completed = false
            )
        )
    }
}