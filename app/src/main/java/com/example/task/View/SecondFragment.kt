package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.SubCategoryAdapter
import com.example.task.Models.Category
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second), SubCategoryAdapter.OnItemClickListener {

    private  lateinit var binding: FragmentSecondBinding
    private lateinit var subCatAdapter: SubCategoryAdapter
    private var subCategoryList=ArrayList<SubCategory>()
    private val viewModel: SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)


        binding.seconIleri.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToThirdFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.secondGeri.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.recyclerSecond.setHasFixedSize(true)
        binding.recyclerSecond.layoutManager= LinearLayoutManager(context)


        subCatAdapter=SubCategoryAdapter(subCategoryList,this)
        binding.recyclerSecond.adapter=subCatAdapter

        //get list from ViewModel
        viewModel.getSubCategoryList().observe(viewLifecycleOwner, Observer {
            subCategoryList.clear()
            subCategoryList.addAll(it)
            subCatAdapter.notifyDataSetChanged()
        })

        //get data from firstFragment
        viewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }
    fun updateUI( category: Category) {
        binding.secFirstCat.setText(category.kategori_ad)
    }

    override fun onItemClick(position: Int) {
        viewModel.setSelectedSubCategory(position)
        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
    }


}