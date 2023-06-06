package it.icsone.listfromapicompose.repositories

import it.icsone.listfromapicompose.models.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface ApiRepository {
    
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): Todo

    companion object {
        private var apiRepository: ApiRepository? = null
        fun getInstance(): ApiRepository {
            if (apiRepository == null) {
                apiRepository = Retrofit.Builder().apply {
                    baseUrl(BASE_URL)
                    addConverterFactory(GsonConverterFactory.create())
                }.build().create(ApiRepository::class.java)
            }
            return apiRepository!!
        }
    }

}