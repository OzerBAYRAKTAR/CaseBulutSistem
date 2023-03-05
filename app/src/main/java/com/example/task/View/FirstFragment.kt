package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.CategoryAdapter
import com.example.task.R
import com.example.task.databinding.FragmentFirstBinding
import com.example.task.Models.Category


class FirstFragment : Fragment(R.layout.fragment_first),CategoryAdapter.OnItemClickListener  {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var catAdapter:CategoryAdapter
    private var categoryList = ArrayList<Category>()
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "İlan Ver-Kategori Seç"

        binding.recyclerFirst.setHasFixedSize(true)
        binding.recyclerFirst.layoutManager=LinearLayoutManager(context)

        catAdapter=CategoryAdapter(categoryList,this)
        binding.recyclerFirst.adapter=catAdapter

        //get list from viewModel
        viewModel.getCategoryList().observe(viewLifecycleOwner, Observer {
            categoryList.clear()
            categoryList.addAll(it)
            catAdapter.notifyDataSetChanged()
        })

    }

    override fun onItemClick(position: Int) {
        viewModel.setSelectedCategory(position)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
    }


}