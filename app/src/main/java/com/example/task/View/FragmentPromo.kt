package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.databinding.FragmentPromoBinding
import com.example.task.databinding.FragmentRewiewBinding

class FragmentPromo : Fragment(R.layout.fragment_promo) {

    private lateinit var binding: FragmentPromoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromoBinding.bind(view)

        binding.promoIleri.setOnClickListener {
            if (binding.checkKosul.isChecked) {
                val action = FragmentPromoDirections.actionFragmentPromoToFinalFragment()
                Navigation.findNavController(it).navigate(action)
            } else {
                Toast.makeText(
                    requireContext(),
                    "İlan verebilmeniz için şartları kabul etmelisiniz.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.promoGeri.setOnClickListener {
            val action = FragmentPromoDirections.actionFragmentPromoToRewiewFragment()
            Navigation.findNavController(it).navigate(action)
        }
        checkBoxControl()

    }

    fun checkBoxControl() {
        binding.check1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check2.isChecked=false
                binding.check3.isChecked=false
                binding.check4.isChecked=false
                binding.check5.isChecked=false
            }

        }
        binding.check2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check1.isChecked=false
                binding.check3.isChecked=false
                binding.check4.isChecked=false
                binding.check5.isChecked=false
            }

        }
        binding.check3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check2.isChecked=false
                binding.check1.isChecked=false
                binding.check4.isChecked=false
                binding.check5.isChecked=false
            }

        }
        binding.check4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check2.isChecked=false
                binding.check3.isChecked=false
                binding.check1.isChecked=false
                binding.check5.isChecked=false
            }

        }
        binding.check5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check2.isChecked=false
                binding.check3.isChecked=false
                binding.check4.isChecked=false
                binding.check1.isChecked=false
            }

        }
    }
}