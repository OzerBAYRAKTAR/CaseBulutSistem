package com.example.task.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products (
    val product_id:Int,
    val product_ad:String,
    val subCategory: SubCategory?,
    val mainCategory: Category?
    ): Parcelable