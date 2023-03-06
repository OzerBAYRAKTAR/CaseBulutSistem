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
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.task.R
import com.example.task.ViewModel.SharedViewModel
import com.example.task.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private  lateinit var binding: FragmentDetailBinding
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private val sharedViewModel: SharedViewModel by activityViewModels()
    var pickedBitmap:Bitmap?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)


        binding.detailImage.setOnClickListener {
            selectImage()
        }

        registerLauncher()
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
    private fun selectImage() {
        // if SDK.Version >= 33
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(requireContext(),permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission.READ_MEDIA_IMAGES)){
                    //rationale
                    Snackbar.make(requireView(),"Galeriye gitmek için izin gerekli",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver",View.OnClickListener {
                        //request Permission
                        permissionLauncher.launch(permission.READ_MEDIA_IMAGES)
                    }).show()
                }else{
                    //permission
                    permissionLauncher.launch(permission.READ_MEDIA_IMAGES)
                }
            }else{
                val intentToGallery=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
            //if sdk.version <33
        }else{
            if(ContextCompat.checkSelfPermission(requireContext(),permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission.READ_EXTERNAL_STORAGE)){
                    //rationale
                    Snackbar.make(requireView(),"Galeriye gitmek için izin gerekli",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver",View.OnClickListener {
                        //request Permission
                        permissionLauncher.launch(permission.READ_EXTERNAL_STORAGE)
                    }).show()
                }else{
                    //permission
                    permissionLauncher.launch(permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                val intentToGallery=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }

    }

    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    val imageData = intentFromResult.data
                    pickedBitmap=MediaStore.Images.Media.getBitmap(requireContext().contentResolver,imageData)
                    pickedBitmap?.let{sharedViewModel.ilan_image= pickedBitmap as Bitmap }
                    binding.detailImage.setImageBitmap(pickedBitmap)
                }
            }

        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                //permission denied
                Toast.makeText(requireContext(), "Permisson needed!", Toast.LENGTH_LONG).show()
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