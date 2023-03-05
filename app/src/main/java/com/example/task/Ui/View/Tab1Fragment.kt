package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.task.Models.Category
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentTab1Binding


class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private  lateinit var binding: FragmentTab1Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()
     var products: Products ?=null
     var category: Category?=null
     var subCategory: SubCategory?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab1Binding.bind(view)

        getData()
        moneyMarks()
        getCategories()

    }
    private fun getData() {
        binding.reviewKategori.isSelected=true
        binding.reviewKategori.isSingleLine=true

        binding.reviewLink.isSelected=true
        binding.reviewLink.isSingleLine=true

        binding.reviewIlanAdi.text=sharedViewModel.ilan_ad
        binding.reviewFiyat.text=sharedViewModel.ilan_fiyat
        binding.reviewLink.text=sharedViewModel.ilan_link
    }
    private fun moneyMarks() {
        when (sharedViewModel.ilan_spinner) {
            "USD"  -> {
                binding.imagePara.setImageResource(R.drawable.dolar)
            }
            "EUR"  -> {
                binding.imagePara.setImageResource(R.drawable.euro)
            }
            "POUND"  -> {
                binding.imagePara.setImageResource(R.drawable.pound)
            }
            "FRANK"  -> {
                binding.imagePara.setImageResource(R.drawable.franc)
            }
            "TL"  -> {
                binding.imagePara.setImageResource(R.drawable.lira)
            }
        }
    }
    fun getCategories() {
        //get data from sharedviewmodel
        sharedViewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer { category ->

            sharedViewModel.getSelectedSubCategory().observe(viewLifecycleOwner, Observer { subCategory ->

                sharedViewModel.getSelectedProducts().observe(viewLifecycleOwner, Observer { product ->

                    binding.reviewKategori.setText("${category.kategori_ad}>${subCategory.subKategori_ad}>${product.product_ad}")
                })
            })
        })


    }

}