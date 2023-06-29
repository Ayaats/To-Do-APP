package com.example.to_doapp.repo

import androidx.lifecycle.LiveData
import com.example.to_doapp.database.TaskDao
import com.example.to_doapp.database.TaskEntry

class TaskRepo(val taskDao: TaskDao) {

    suspend fun insert(taskEntry: TaskEntry)=taskDao.insert(taskEntry)

    suspend fun updateData(taskEntry: TaskEntry)=taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry)=taskDao.delete(taskEntry)

    suspend fun deleteAll(){ taskDao.deleteAll() }

    fun getAllTasks() = taskDao.getAllTasks()
    fun getAllPriorityTasks() = taskDao.getAllPriorityTasks()

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>> {
        return taskDao.searchDatabase(searchQuery)
    }
}