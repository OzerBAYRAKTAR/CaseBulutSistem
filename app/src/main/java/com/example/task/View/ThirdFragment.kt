package com.example.task.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.ProductAdapter
import com.example.task.Adapters.SubCategoryAdapter
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.R
import com.example.task.databinding.FragmentSecondBinding
import com.example.task.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var proAdapter: ProductAdapter
    private val args by navArgs<ThirdFragmentArgs>()
    private val args2 by navArgs<SecondFragmentArgs>()

    private var productList=ArrayList<Products>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdBinding.bind(view)

        binding.thrdSecCat.text=args.thirdname
        binding.thrdFirstCat.text=args2.secondname

        binding.thirdIleri.setOnClickListener {
            val action=ThirdFragmentDirections.actionThirdFragmentToDetailFragment(null)
            Navigation.findNavController(it).navigate(action)
        }

        binding.thirdGeri.setOnClickListener {
            val action=ThirdFragmentDirections.actionThirdFragmentToSecondFragment(null)
            Navigation.findNavController(it).navigate(action)
        }

        binding.recyclerThird.setHasFixedSize(true)
        binding.recyclerThird.layoutManager= LinearLayoutManager(context)
        productList=ArrayList()

        val p1= Products(1,"Samsung",null,null)
        val p2= Products(2,"Sony",null,null)
        val p3= Products(3,"Philips",null,null)
        val p4= Products(4,"Lg",null,null)
        val p5= Products(5,"Vestel",null,null)
        val p6= Products(6,"Arçelik",null,null)
        val p7= Products(7,"Altus",null,null)
        val p8= Products(8,"Beko",null,null)

        productList.add(p1)
        productList.add(p2)
        productList.add(p3)
        productList.add(p4)
        productList.add(p5)
        productList.add(p6)
        productList.add(p7)
        productList.add(p8)

        proAdapter= ProductAdapter(productList)
        binding.recyclerThird.adapter=proAdapter

    }


}