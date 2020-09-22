package com.rober.sampleappmvi.data.api

import com.rober.sampleappmvi.data.model.TodoTask

class RestApiImpl(private val restApiService: RestApiService): RestApi {

    override suspend fun getTodoTasks(): List<TodoTask> {
        return restApiService.listTodo()
    }
}