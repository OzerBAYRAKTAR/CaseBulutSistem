package com.example.task.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SubCategory(
    val subKategori_id:Int,
    val subKategori_ad:String,
    val category: Category?
    ): Parcelable
