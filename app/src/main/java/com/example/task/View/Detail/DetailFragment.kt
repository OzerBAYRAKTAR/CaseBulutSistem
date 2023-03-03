package com.example.task.View.Detail

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task.R
import com.example.task.View.ThirdFragmentArgs
import com.example.task.data.RegisterModel
import com.example.task.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private  lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel:DetailViewModel
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        binding.detailThirdCat.text=args.detailname

        viewModel=ViewModelProvider(this).get(DetailViewModel::class.java)

        val currencies=resources.getStringArray(R.array.currency)

        val categoryAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,currencies)
        categoryAdapter.notifyDataSetChanged()
        binding.spinnerMoney.adapter=categoryAdapter

        binding.spinnerMoney.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                binding.spinnerText.setText(p0?.getItemAtPosition(p2).toString())

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.detailSave.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val name=binding.ilanAdInput.text.toString()
        val link=binding.LinkInput.text.toString()
        val amount=binding.fiyatInput.text
        val spinner=binding.spinnerText.text.toString()
        val description=binding.descriptionInput.text.toString()



        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(link) ||
            TextUtils.isEmpty(spinner)  || amount!!.isEmpty()){
            Toast.makeText(requireContext(), "Lütfen tüm boşlukları doldurun.", Toast.LENGTH_SHORT).show()
        }else{
            if ( description.length < 50){
                Toast.makeText(requireContext(), "Açıklama minimum 50 karakter olmalıdır.", Toast.LENGTH_SHORT).show()
            }else{
                val register=RegisterModel(name,link,Integer.parseInt(amount.toString()),spinner,description,0)
                //add to database
                viewModel.addRegister(register)

                val action=DetailFragmentDirections.actionDetailFragmentToRewiewFragment(register)
                findNavController().navigate(action)
                Toast.makeText(requireContext(), "Kayıt Yapıldı.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun inputCheck(
        name: String,
        link: String,
        amount: Editable,
        spinner: String,
    ): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(link) ||
                TextUtils.isEmpty(spinner)  || amount.isEmpty())
    }
}