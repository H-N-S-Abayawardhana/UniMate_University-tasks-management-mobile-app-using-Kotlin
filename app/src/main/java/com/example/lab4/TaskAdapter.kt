package com.example.lab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R

class TaskAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        var moduleTextView: TextView = itemView.findViewById(R.id.moduleTextView)
        var dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        var descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.titleTextView.text = task.title
        holder.typeTextView.text = task.type
        holder.moduleTextView.text = task.module
        holder.dateTextView.text = task.date
        holder.descriptionTextView.text = task.description
    }

    override fun getItemCount() = taskList.size
}
