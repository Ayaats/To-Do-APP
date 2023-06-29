package com.example.to_doapp.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.database.TaskEntry
import com.example.to_doapp.databinding.RowLayoutBinding

class TaskAdapter(private val clickListener: TaskClickListener) : androidx.recyclerview.widget.ListAdapter <TaskEntry,TaskAdapter.ViewHolder> (TaskDiffCallback) {
    companion object TaskDiffCallback : DiffUtil.ItemCallback<TaskEntry>(){
        override fun areItemsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem==newItem
    }

    class ViewHolder (private val binding : RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
       fun bind(taskEntry: TaskEntry , clickListener: TaskClickListener){
          binding.taskEntry = taskEntry
          binding.clickListener = clickListener
          binding.executePendingBindings()
       }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current , clickListener)
    }

}
class TaskClickListener(val clickListener:(taskEntry:TaskEntry)-> Unit){
    fun onClick(taskEntry: TaskEntry) = clickListener(taskEntry)
}