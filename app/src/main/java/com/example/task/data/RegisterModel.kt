package com.example.task.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "register_table")
@Parcelize
data class RegisterModel(
    val name:String,
    val link:String,
    val amount:Int,
    val currency:String,
    val description:String,
    @PrimaryKey(autoGenerate = true) val id: Int=0

): Parcelable {
}