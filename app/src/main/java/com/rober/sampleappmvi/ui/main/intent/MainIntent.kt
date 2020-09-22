package com.rober.sampleappmvi.ui.main.intent

sealed class MainIntent {

    object FetchTodoTasks: MainIntent()
}