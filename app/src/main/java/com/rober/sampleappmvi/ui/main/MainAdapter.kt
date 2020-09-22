package com.rober.sampleappmvi.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rober.sampleappmvi.R
import com.rober.sampleappmvi.data.model.TodoTask

class MainAdapter(
    private var listTodoTasks: List<TodoTask>
): RecyclerView.Adapter<MainAdapter.MainAdapterRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapterRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_task_viewholder, parent, false)
        return MainAdapterRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapterRecyclerViewHolder, position: Int) {
        holder.bind(listTodoTasks[position])
    }

    override fun getItemCount(): Int {
        return listTodoTasks.size
    }

    fun setTodoTasks(newListTodoTask: List<TodoTask>){
        listTodoTasks = newListTodoTask
    }

    class MainAdapterRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var id: TextView = itemView.findViewById(R.id.id)
        var userId: TextView = itemView.findViewById(R.id.user_id)
        var taskTitle: TextView = itemView.findViewById(R.id.title_task)
        var taskCompleted: TextView = itemView.findViewById(R.id.task_completed)

        fun bind(todoTask: TodoTask){
            id.text = "ID = ${todoTask.id}"
            userId.text = "ID = ${todoTask.userId}"
            taskTitle.text = "Task title: ${todoTask.title}"
            taskCompleted.text = "Task completed: ${todoTask.completed}"
        }
    }
}