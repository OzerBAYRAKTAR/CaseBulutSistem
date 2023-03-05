package com.example.task.View.Tab1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.task.R
import com.example.task.View.SharedViewModel
import com.example.task.databinding.FragmentTab1Binding


class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private  lateinit var binding: FragmentTab1Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab1Binding.bind(view)

        binding.reviewIlanAdi.text=sharedViewModel.ilan_ad
        binding.reviewFiyat.text=sharedViewModel.ilan_fiyat
        binding.reviewLink.text=sharedViewModel.ilan_link

        when (sharedViewModel.ilan_spinner) {
            "USD"  -> {
                binding.imagePara.setImageResource(R.drawable.dolar)
            }
            "EUR"  -> {
                binding.imagePara.setImageResource(R.drawable.euro)
            }
            "POUND"  -> {
                binding.imagePara.setImageResource(R.drawable.pound)
            }
            "FRANK"  -> {
                binding.imagePara.setImageResource(R.drawable.franc)
            }
            "TL"  -> {
                binding.imagePara.setImageResource(R.drawable.lira)
            }
        }

    }
}