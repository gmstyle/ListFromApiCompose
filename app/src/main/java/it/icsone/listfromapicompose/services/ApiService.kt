package it.icsone.listfromapicompose.services

import it.icsone.listfromapicompose.models.Todo
import it.icsone.listfromapicompose.repositories.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiService(private val apiRepository: ApiRepository) {

    fun getTodos(): Flow<List<Todo>> {
        return flow {
            val todoList = apiRepository.getTodos()
            emit(todoList)
        }.flowOn(Dispatchers.IO)
    }

    fun getTodoById(id: Int): Flow<Todo> {
        return flow {
            val todo = apiRepository.getTodoById(id)
            emit(todo)
        }.flowOn(Dispatchers.IO)
    }
}