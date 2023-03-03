package com.example.task.Adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.View.Tab1.Tab1Fragment
import com.example.task.View.Tab2.Tab2Fragment

class FragmentPageAdapter(
    fm:FragmentManager,
    lifecycle: Lifecycle
):FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
            return 2
    }

    override fun createFragment(position: Int): Fragment {
       return if (position==0)
           Tab1Fragment()
           else
               Tab2Fragment()
       }
    }
