package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.CategoryAdapter
import com.example.task.R
import com.example.task.databinding.FragmentFirstBinding
import com.example.task.Models.Category


class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var catAdapter:CategoryAdapter
    private var categoryList=ArrayList<Category>()
    private lateinit var categoryName:Category
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding.recyclerFirst.setHasFixedSize(true)
        binding.recyclerFirst.layoutManager=LinearLayoutManager(context)
        categoryList=ArrayList()

        val c1=Category(1,"Telefon")
        val c2=Category(2,"Bilgisayar")
        val c3=Category(3,"Diğer")

        categoryList.add(c1)
        categoryList.add(c2)
        categoryList.add(c3)

        catAdapter=CategoryAdapter(categoryList)
        binding.recyclerFirst.adapter=catAdapter



    }
}