package com.rober.sampleappmvi.data.api

import com.rober.sampleappmvi.data.model.TodoTask
import retrofit2.http.GET

interface RestApiService {

    @GET("todos")
    suspend fun listTodo(): List<TodoTask>
    
}