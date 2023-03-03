package com.example.task.View.Tab1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.View.Detail.DetailViewModel
import com.example.task.databinding.FragmentTab1Binding


class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private  lateinit var binding: FragmentTab1Binding
    private lateinit var viewModel: Tab1ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab1Binding.bind(view)

        viewModel=ViewModelProvider(this).get(Tab1ViewModel::class.java)


    }
}