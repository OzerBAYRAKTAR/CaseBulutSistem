package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
import com.example.task.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third),ProductAdapter.OnItemClickListener {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var proAdapter: ProductAdapter
    private var productsList=ArrayList<Products>()
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdBinding.bind(view)

        binding.thirdIleri.setOnClickListener {
            val action=ThirdFragmentDirections.actionThirdFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.thirdGeri.setOnClickListener {
            val action=ThirdFragmentDirections.actionThirdFragmentToSecondFragment()
            Navigation.findNavController(it).navigate(action)
        }

        //get list from viewmodel
        viewModel.getProductList().observe(viewLifecycleOwner, Observer {
            productsList.clear()
            productsList.addAll(it)
            proAdapter.notifyDataSetChanged()
        })

        //get data from firstFragment
        viewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer {
            updateFirstUi(it)
        })
        //get data from SecondFragment
        viewModel.getSelectedSubCategory().observe(viewLifecycleOwner, Observer {
            updateSecondUi(it)
        })

        binding.recyclerThird.setHasFixedSize(true)
        binding.recyclerThird.layoutManager= LinearLayoutManager(context)

        proAdapter= ProductAdapter(productsList,this)
        binding.recyclerThird.adapter=proAdapter

    }

    fun updateFirstUi(category:Category) {
        binding.thrdFirstCat.setText(category.kategori_ad)
    }
    fun updateSecondUi(subCategory: SubCategory) {
        binding.thrdSecCat.setText(subCategory.subKategori_ad)
    }

    override fun onItemClick(position: Int) {
        viewModel.setSelectedProducts(position)
        findNavController().navigate(R.id.action_thirdFragment_to_detailFragment)
    }


}