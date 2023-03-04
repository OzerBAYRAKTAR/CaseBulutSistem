package com.example.task.View

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.Models.Category
import com.example.task.Models.SubCategory

class SharedViewModel() : ViewModel() {

    //categoryFragment
    private var mutableCategory = MutableLiveData<Category>()
    private var mutableCategoryList = MutableLiveData<List<Category>>()
    private lateinit var category: Category

    fun getCategoryList(): LiveData<List<Category>> {
        categoryData()
        return mutableCategoryList
    }

    fun getSelectedCategory(): LiveData<Category> {
        return mutableCategory
    }
    fun setSelectedCategory(position: Int) {
        category= mutableCategoryList.value!!.get(position)
        mutableCategory.postValue(category)
    }

    fun categoryData() {
        val categoryList=ArrayList<Category>()
        val c1=Category(1,"Telefon")
        val c2=Category(2,"Bilgisayar")
        val c3=Category(3,"Diğer")

        categoryList.add(c1)
        categoryList.add(c2)
        categoryList.add(c3)
        mutableCategoryList.postValue(categoryList)
    }

    //SecondFragment
    private var mutableSubCategory = MutableLiveData<SubCategory>()
    private var mutableSubCategoryList = MutableLiveData<List<SubCategory>>()
    private lateinit var subcategory: SubCategory

    fun subCategoryData() {
        val subCategoryList=ArrayList<SubCategory>()
        val sc1=SubCategory(1,"Televizyon",null)
        val sc2=SubCategory(2,"Medya Oynatıcı",null)
        val sc3=SubCategory(3,"Monitor",null)
        val sc4=SubCategory(4,"Drone",null)
        val sc5=SubCategory(5,"Uydu Alıcısı",null)
        val sc6=SubCategory(6,"Kulaklık",null)
        val sc7=SubCategory(7,"Sanal Gerçeklik Gözlüğü",null)
        val sc8=SubCategory(8,"Modem",null)

        subCategoryList.add(sc1)
        subCategoryList.add(sc2)
        subCategoryList.add(sc3)
        subCategoryList.add(sc4)
        subCategoryList.add(sc5)
        subCategoryList.add(sc6)
        subCategoryList.add(sc7)
        subCategoryList.add(sc8)
        mutableSubCategoryList.postValue(subCategoryList)
    }

    fun getSubCategoryList(): LiveData<List<SubCategory>> {
        categoryData()
        return mutableSubCategoryList
    }

    fun getSelectedSubCategory(): LiveData<SubCategory> {
        return mutableSubCategory
    }
    fun setSelectedSubCategory(position: Int) {
        subcategory= mutableSubCategoryList.value!!.get(position)
        mutableSubCategory.postValue(subcategory)
    }




}