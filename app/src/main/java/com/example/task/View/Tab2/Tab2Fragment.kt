package com.example.task.View.Tab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.task.R
import com.example.task.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment(R.layout.fragment_tab2) {

    private lateinit var binding: FragmentTab2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab2Binding.bind(view)
    }
}