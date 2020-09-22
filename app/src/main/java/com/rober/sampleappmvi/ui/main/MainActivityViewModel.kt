package com.rober.sampleappmvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rober.sampleappmvi.data.model.TodoTask
import com.rober.sampleappmvi.data.repository.MainRepository
import com.rober.sampleappmvi.ui.main.intent.MainIntent
import com.rober.sampleappmvi.ui.main.state.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    val mainState: StateFlow<MainState>
        get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainIntent.FetchTodoTasks -> fetchTodoTasks()
                }
            }
        }
    }

    private suspend fun fetchTodoTasks(){

        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try{
                MainState.LoadTasks(repository.getTodoTasks())
            }catch (e: Exception){
                MainState.Error(e.message)
            }
        }
    }
}