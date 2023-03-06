package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.SubCategoryAdapter
import com.example.task.Models.Category
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second), SubCategoryAdapter.OnItemClickListener {

    private  lateinit var binding: FragmentSecondBinding
    private lateinit var subCatAdapter: SubCategoryAdapter
    private var subCategoryList=ArrayList<SubCategory>()
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var progrss:ProgressBar
    private lateinit var txtview: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "İlan Ver-Kategori Seç"




        binding.recyclerSecond.setHasFixedSize(true)
        binding.recyclerSecond.layoutManager= LinearLayoutManager(context)

        subCatAdapter=SubCategoryAdapter(subCategoryList,this)
        binding.recyclerSecond.adapter=subCatAdapter

        getMain()
        goBack()
        getList()
        getCategori()

    }
    private fun getMain() {
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(1/5)")
        progrss.setProgress(20)

    }
    private fun goBack() {
        binding.secondGeri.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    //get list from ViewModel
    private fun getList() {
        viewModel.getSubCategoryList().observe(viewLifecycleOwner, Observer {
            subCategoryList.clear()
            subCategoryList.addAll(it)
            subCatAdapter.notifyDataSetChanged()
        })
    }
    //get category from viewmodel
    private fun getCategori() {
        viewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer { category ->
            binding.secFirstCat.setText("${category.kategori_ad}")
        })
    }

    //go next fragment when click recyclerview item
    override fun onItemClick(position: Int) {
        viewModel.setSelectedSubCategory(position)
        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
    }


}