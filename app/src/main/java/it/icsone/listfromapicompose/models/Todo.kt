package it.icsone.listfromapicompose.models

data class Todo(
    var userId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var completed: Boolean? = null
)