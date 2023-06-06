package it.icsone.listfromapicompose.ui.home.widgets

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import it.icsone.listfromapicompose.models.Todo

@Composable
fun DetailAlertDialog(
    todo: Todo,
    onConfirm: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onConfirm() },
        title = {
            Text(
                text = todo.id.toString()
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }) {
                Text(text = "Close")
            }
        },
        /*dismissButton = { TextButton(onClick = {
            onConfirm()
        }) {
            Text(text = "Dismiss")
        }},*/
        text = { Text(text = todo.title ?: "") },

        shape = RoundedCornerShape(30.dp)
    )

}
