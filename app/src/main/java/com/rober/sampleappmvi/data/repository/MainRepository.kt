package com.rober.sampleappmvi.data.repository

import com.rober.sampleappmvi.data.api.RestApi

class MainRepository(private val restApi: RestApi) {

    suspend fun getTodoTasks() = restApi.getTodoTasks()
}