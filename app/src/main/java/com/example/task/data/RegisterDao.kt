package com.example.task.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDao {

    @Query("SELECT * from register_table")
    fun getItems(): LiveData<List<RegisterModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(register: RegisterModel)
}