package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.CategoryAdapter
import com.example.task.Adapters.SubCategoryAdapter
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private  lateinit var binding: FragmentSecondBinding
    private lateinit var subCatAdapter: SubCategoryAdapter
    private val args by navArgs<SecondFragmentArgs>()
    private var subCategoryList=ArrayList<SubCategory>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        binding.secFirstCat.text=args.secondname

        binding.seconIleri.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToThirdFragment(null)
            Navigation.findNavController(it).navigate(action)
        }

        binding.secondGeri.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.recyclerSecond.setHasFixedSize(true)
        binding.recyclerSecond.layoutManager= LinearLayoutManager(context)
        subCategoryList=ArrayList()

        val sc1=SubCategory(1,"Televizyon",null)
        val sc2=SubCategory(2,"Medya Oynatıcı",null)
        val sc3=SubCategory(3,"Monitor",null)
        val sc4=SubCategory(4,"Drone",null)
        val sc5=SubCategory(5,"Uydu Alıcısı",null)
        val sc6=SubCategory(6,"Kulaklık",null)
        val sc7=SubCategory(7,"Sanal Gerçeklik Gözlüğü",null)
        val sc8=SubCategory(8,"Modem",null)

        subCategoryList.add(sc1)
        subCategoryList.add(sc2)
        subCategoryList.add(sc3)
        subCategoryList.add(sc4)
        subCategoryList.add(sc5)
        subCategoryList.add(sc6)
        subCategoryList.add(sc7)
        subCategoryList.add(sc8)

        subCatAdapter=SubCategoryAdapter(subCategoryList)
        binding.recyclerSecond.adapter=subCatAdapter
    }




}