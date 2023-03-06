package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.ProductAdapter
import com.example.task.Models.Category
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third),ProductAdapter.OnItemClickListener {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var proAdapter: ProductAdapter
    private var productsList=ArrayList<Products>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdBinding.bind(view)



        binding.recyclerThird.setHasFixedSize(true)
        binding.recyclerThird.layoutManager= LinearLayoutManager(context)

        proAdapter= ProductAdapter(productsList,this)
        binding.recyclerThird.adapter=proAdapter

        getMain()
        goBack()
        getList()
        getCategories()

    }
    private fun getMain() {
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(1/5)")
        progrss.setProgress(20)
    }
    private fun goBack() {
        binding.thirdGeri.setOnClickListener {
            val action=ThirdFragmentDirections.actionThirdFragmentToSecondFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun getList() {
        //get list from viewmodel
        sharedViewModel.getProductList().observe(viewLifecycleOwner, Observer {
            productsList.clear()
            productsList.addAll(it)
            proAdapter.notifyDataSetChanged()
        })
    }
    private fun getCategories() {
        //get data from sharedviewmodel
        sharedViewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer { category ->

            sharedViewModel.getSelectedSubCategory().observe(viewLifecycleOwner, Observer { subCategory ->

                    binding.thrdFirstCat.setText("${category.kategori_ad}>${subCategory.subKategori_ad}")
                })
            })
    }
    //go next fragment when click recyclerview item
    override fun onItemClick(position: Int) {
        sharedViewModel.setSelectedProducts(position)
        findNavController().navigate(R.id.action_thirdFragment_to_detailFragment)
    }


}