package com.example.to_doapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.database.TaskDatabase
import com.example.to_doapp.database.TaskEntry
import com.example.to_doapp.repo.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application){
  private val taskDao= TaskDatabase.getDatabase(application).taskDao()
  private val repository :  TaskRepo
  val getAllTasks : LiveData<List<TaskEntry>>
  init {
      repository = TaskRepo(taskDao)
      getAllTasks = repository.getAllTasks()
  }

  fun insert(taskEntry: TaskEntry) {
      viewModelScope.launch(Dispatchers.IO) {
          repository.insert(taskEntry)
      }
  }
  fun delete(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(taskEntry)
        }
  }
  fun update(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(taskEntry)
        }
  }
    fun delateAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}