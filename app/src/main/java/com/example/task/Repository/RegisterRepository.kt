package com.example.task.Repository

import androidx.lifecycle.LiveData
import com.example.task.data.RegisterDao
import com.example.task.data.RegisterModel

class RegisterRepository(private val registerDao: RegisterDao) {

    val getData: LiveData<List<RegisterModel>> = registerDao.getItems()

    suspend fun addRegister(registerModel: RegisterModel) {
        registerDao.insert(registerModel)
    }
    fun getData(): LiveData<List<RegisterModel>> {
        return registerDao.getItems()
    }
}