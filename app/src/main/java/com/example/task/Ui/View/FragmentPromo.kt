package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.databinding.FragmentPromoBinding

class FragmentPromo : Fragment(R.layout.fragment_promo) {

    private lateinit var binding: FragmentPromoBinding
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromoBinding.bind(view)

        getMain()
        controlBoxs()
        checkBoxControl()
        goBack()

    }
    private fun getMain() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Reklam"
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(4/5)")
        progrss.setProgress(80)
    }
    private fun controlBoxs() {
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
    }
    private fun goBack() {
        binding.promoGeri.setOnClickListener {
            val action = FragmentPromoDirections.actionFragmentPromoToRewiewFragment()
            Navigation.findNavController(it).navigate(action)
        }
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