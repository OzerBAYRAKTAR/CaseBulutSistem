package com.example.task.View

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.task.Adapters.FragmentPageAdapter
import com.example.task.R
import com.example.task.databinding.FragmentRewiewBinding
import com.google.android.material.tabs.TabLayout



class ReviewFragment : Fragment(R.layout.fragment_rewiew) {


    private  var _binding: FragmentRewiewBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter:FragmentPageAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentRewiewBinding.inflate(inflater,container,false)

        if (sharedViewModel.ilan_image == null) {
            binding.reviewImage.setImageResource(R.drawable.ic_placeholder)
        }else{
            binding.reviewImage.setImageBitmap(sharedViewModel.ilan_image)
        }


        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Ön İzleme"

        viewPagerAdapter= FragmentPageAdapter(childFragmentManager,lifecycle)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("First"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Second"))

        binding.viewP.adapter=viewPagerAdapter

        binding.reviewIleri.setOnClickListener {
            val action=ReviewFragmentDirections.actionRewiewFragmentToFragmentPromo()
            Navigation.findNavController(it).navigate(action)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewP.currentItem=tab.position
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        binding.viewP.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
        return binding.root
    }




}