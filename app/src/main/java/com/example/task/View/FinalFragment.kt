package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.databinding.FragmentDetailBinding
import com.example.task.databinding.FragmentFinalBinding
import com.example.task.databinding.FragmentPromoBinding

class FinalFragment : Fragment(R.layout.fragment_final) {
    private  lateinit var binding: FragmentFinalBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFinalBinding.bind(view)

        binding.imageHome.setOnClickListener {
            val action=FinalFragmentDirections.actionFinalFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}