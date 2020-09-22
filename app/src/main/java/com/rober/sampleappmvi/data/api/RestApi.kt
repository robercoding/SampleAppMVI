package com.rober.sampleappmvi.data.api

import com.rober.sampleappmvi.data.model.TodoTask

interface RestApi {

    suspend fun getTodoTasks(): List<TodoTask>
}