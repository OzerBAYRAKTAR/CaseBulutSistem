package com.example.task.View.Detail

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.util.Patterns.WEB_URL
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.util.PatternsCompat.WEB_URL
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.Models.Category
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.View.SharedViewModel
import com.example.task.databinding.FragmentDetailBinding
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private  lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel:DetailViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

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

            }
        }

        binding.detailSave.setOnClickListener {
            val name=binding.ilanAdInput.text.toString()
            val link=binding.LinkInput.text.toString()
            val amount=binding.fiyatInput.text.toString()
            val spinner=binding.spinnerText.text.toString()
            val description=binding.descriptionInput.text.toString()

            sharedViewModel.ilan_ad=name
            sharedViewModel.ilan_link=link
            sharedViewModel.ilan_fiyat=amount
            sharedViewModel.ilan_spinner=spinner
            sharedViewModel.ilan_aciklama=description

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(link) ||
                TextUtils.isEmpty(spinner)  || amount.isEmpty()) {
                Toast.makeText(requireContext(), "Lütfen tüm boşlukları doldurun.", Toast.LENGTH_SHORT).show()
            }else{
                if ( description.length < 50){
                    Toast.makeText(requireContext(), "Açıklama minimum 50 karakter olmalıdır.", Toast.LENGTH_SHORT).show()

                }
                if (!Patterns.WEB_URL.matcher(link).matches()){
                    Toast.makeText(requireContext(), "Lütfen Linki dogrulayın", Toast.LENGTH_SHORT).show()
                }
                else{
                    val action=DetailFragmentDirections.actionDetailFragmentToRewiewFragment()
                    findNavController().navigate(action)
                }
            }
        }

        //get data from firstFragment
        sharedViewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer {
            updateFirstUi(it)
        })
        //get data from SecondFragment
        sharedViewModel.getSelectedSubCategory().observe(viewLifecycleOwner, Observer {
            updateSecondUi(it)
        })
        //get data from thirdFragment
        sharedViewModel.getSelectedProducts().observe(viewLifecycleOwner, Observer {
            updateThirdUi(it)
        })
        checkLink()



    }
    //check link
    fun checkLink() {
        binding.checkLink.setOnClickListener {
            val input=binding.LinkInput.text.toString()
            if (Patterns.WEB_URL.matcher(input).matches()) {
                binding.checkLink.setImageResource(R.drawable.checked)
                Toast.makeText(requireContext(), "Link Dogru", Toast.LENGTH_SHORT).show()
            }else{
                binding.checkLink.setImageResource(R.drawable.wrong)
                Toast.makeText(requireContext(), "Link Yanlis", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateThirdUi(products: Products) {
        binding.detailThirdCat.setText(products.product_ad)
    }

    private fun updateSecondUi(subCategory: SubCategory) {
        binding.detailSecCat.setText(subCategory.subKategori_ad)
    }

    private fun updateFirstUi(category: Category) {
        binding.detailFirstCat.setText(category.kategori_ad)
    }
    fun isValidUrl(url:String) :Boolean{
        try {
            URL(url)
            return true
        } catch (e: Exception) {
            return false
        }
}
}