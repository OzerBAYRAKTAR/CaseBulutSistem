package com.example.task.ViewModel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.Models.Category
import com.example.task.Models.Products
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
        subCategoryData()
        return mutableSubCategoryList
    }

    fun getSelectedSubCategory(): LiveData<SubCategory> {
        return mutableSubCategory
    }
    fun setSelectedSubCategory(position: Int) {
        subcategory= mutableSubCategoryList.value!!.get(position)
        mutableSubCategory.postValue(subcategory)
    }

    //thirdFragment
    private var mutableProducts = MutableLiveData<Products>()
    private var mutableProducstList = MutableLiveData<List<Products>>()
    private lateinit var products: Products

    fun productsData() {
        val productList=ArrayList<Products>()
        val p1= Products(1,"Samsung",null,null)
        val p2= Products(2,"Sony",null,null)
        val p3= Products(3,"Philips",null,null)
        val p4= Products(4,"Lg",null,null)
        val p5= Products(5,"Vestel",null,null)
        val p6= Products(6,"Arçelik",null,null)
        val p7= Products(7,"Altus",null,null)
        val p8= Products(8,"Beko",null,null)

        productList.add(p1)
        productList.add(p2)
        productList.add(p3)
        productList.add(p4)
        productList.add(p5)
        productList.add(p6)
        productList.add(p7)
        productList.add(p8)
        mutableProducstList.postValue(productList)
    }

    fun getProductList(): LiveData<List<Products>> {
        productsData()
        return mutableProducstList
    }

    fun getSelectedProducts(): LiveData<Products> {
        return mutableProducts
    }
    fun setSelectedProducts(position: Int) {
        products= mutableProducstList.value!!.get(position)
        mutableProducts.postValue(products)
    }

    //for detailview items
    lateinit var ilan_ad:String
    lateinit var ilan_link:String
    lateinit var ilan_fiyat:String
    lateinit var ilan_spinner:String
    lateinit var ilan_aciklama:String
    var ilan_image: Bitmap ?=null


}