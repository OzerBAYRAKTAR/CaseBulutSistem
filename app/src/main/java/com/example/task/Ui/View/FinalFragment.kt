package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentFinalBinding

class FinalFragment : Fragment(R.layout.fragment_final) {
    private  lateinit var binding: FragmentFinalBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFinalBinding.bind(view)

        if (sharedViewModel.ilan_image == null) {
            binding.imageFinal.setImageResource(R.drawable.ic_placeholder)
        }else{
            binding.imageFinal.setImageBitmap(sharedViewModel.ilan_image)
        }

        getMain()
        goHome()

    }
    private fun getMain() {
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(5/5)")
        progrss.setProgress(100)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Tebrikler"
    }
    //go home when press imageview
    private fun goHome() {
        binding.imageHome.setOnClickListener {
            val action=FinalFragmentDirections.actionFinalFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}