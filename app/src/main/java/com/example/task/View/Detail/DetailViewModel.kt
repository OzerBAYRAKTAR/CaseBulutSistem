package com.example.task.View.Detail

import android.app.Application
import androidx.lifecycle.*
import com.example.task.Repository.RegisterRepository
import com.example.task.data.AppDatabase
import com.example.task.data.RegisterDao
import com.example.task.data.RegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application){

    private val getAllData:LiveData<List<RegisterModel>>
    private val repository:RegisterRepository

    init {
        val registerDao=AppDatabase.invoke(application).registerDao()
        repository= RegisterRepository(registerDao)
        getAllData=repository.getData
    }

    fun addRegister(registerModel: RegisterModel) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addRegister(registerModel)
        }
    }
}