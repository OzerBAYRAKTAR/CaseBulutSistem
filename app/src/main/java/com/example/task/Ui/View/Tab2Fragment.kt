package com.example.task.Ui.View

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentTab1Binding
import com.example.task.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment(R.layout.fragment_tab2) {

    private lateinit var binding: FragmentTab2Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab2Binding.bind(view)

        binding.tab2Aciklama.text=sharedViewModel.ilan_aciklama
    }
}