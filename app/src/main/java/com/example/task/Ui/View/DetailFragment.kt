package com.example.task.Ui.View

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.task.Util.Constants
import com.example.task.Models.Category
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private  lateinit var binding: FragmentDetailBinding
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    var pickedBitmap:Bitmap?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)


        binding.detailImage.setOnClickListener {
            pickImageGallery()
        }

        getMain()
        saveData()
        getSpinner()
        goBack()
        checkLink()
        getCategories()

    }
    private fun getMain() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "İlan Detayları"
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(2/5)")
        progrss.setProgress(40)
    }

    private fun saveData() {
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
    }
    private fun getSpinner() {

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
    }
    private fun goBack() {
        binding.detailGeri.setOnClickListener {
            val action= DetailFragmentDirections.actionDetailFragmentToThirdFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun pickImageGallery(){
        val intentToGallery=Intent(Intent.ACTION_PICK)
        intentToGallery.type="image/*"
        startActivityForResult(intentToGallery, Constants.IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data?.data != null) {
                pickedBitmap=MediaStore.Images.Media.getBitmap(requireContext().contentResolver,data.data)
                pickedBitmap?.let{sharedViewModel.ilan_image= pickedBitmap as Bitmap }
                binding.detailImage.setImageBitmap(pickedBitmap)
            }
        }
    }
    //get all categories
    private fun getCategories() {
            //get data from sharedviewmodel
            sharedViewModel.getSelectedCategory().observe(viewLifecycleOwner, Observer { category ->

                sharedViewModel.getSelectedSubCategory().observe(viewLifecycleOwner, Observer { subCategory ->

                    sharedViewModel.getSelectedProducts().observe(viewLifecycleOwner, Observer { product ->
                        binding.detailFirstCat.setText("${category.kategori_ad}>${subCategory.subKategori_ad}>${product.product_ad}")
                    })
                })
            })
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

}