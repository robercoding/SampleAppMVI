package com.rober.sampleappmvi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rober.sampleappmvi.R
import com.rober.sampleappmvi.data.api.RestApiImpl
import com.rober.sampleappmvi.data.api.RetrofitBuilder
import com.rober.sampleappmvi.data.model.TodoTask
import com.rober.sampleappmvi.ui.main.intent.MainIntent
import com.rober.sampleappmvi.ui.main.state.MainState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels { MainViewModelFactory(RestApiImpl(RetrofitBuilder.apiService))}
    private var mainAdapter = MainAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupClicks()
        observeViewModel()
    }

    private fun setupClicks(){
        button_tasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks)
            }
        }
    }

    private fun setupUI(){
        recyclerview_tasks.layoutManager = LinearLayoutManager(this)

        recyclerview_tasks.run {
            adapter = mainAdapter
        }
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect {mainState->
                when(mainState){
                    is MainState.Loading -> progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }

                    is MainState.Error -> {
                        recyclerview_tasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>){
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }
}