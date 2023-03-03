package com.example.task.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val kategori_id:Int,
    val kategori_ad:String
    ): Parcelable
