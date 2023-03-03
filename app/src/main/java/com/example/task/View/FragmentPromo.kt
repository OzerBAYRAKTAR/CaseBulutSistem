package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.databinding.FragmentPromoBinding
import com.example.task.databinding.FragmentRewiewBinding

class FragmentPromo : Fragment(R.layout.fragment_promo) {

private  lateinit var binding: FragmentPromoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromoBinding.bind(view)

        binding.promoIleri.setOnClickListener {
            val action=FragmentPromoDirections.actionFragmentPromoToFinalFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.promoGeri.setOnClickListener {
            val action=FragmentPromoDirections.actionFragmentPromoToRewiewFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}