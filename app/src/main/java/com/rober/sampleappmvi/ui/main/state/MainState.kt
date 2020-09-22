package com.rober.sampleappmvi.ui.main.state

import com.rober.sampleappmvi.data.model.TodoTask

sealed class MainState {

    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>): MainState()
    data class Error(val error: String?): MainState()
}