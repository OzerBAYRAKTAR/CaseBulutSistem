package com.example.task.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.Adapters.CategoryAdapter
import com.example.task.R
import com.example.task.databinding.FragmentFirstBinding
import com.example.task.Models.Category
import com.example.task.ViewModel.SharedViewModel


class FirstFragment : Fragment(R.layout.fragment_first),CategoryAdapter.OnItemClickListener  {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var catAdapter:CategoryAdapter
    private var categoryList = ArrayList<Category>()
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var progrss: ProgressBar
    private lateinit var txtview: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)


        binding.recyclerFirst.setHasFixedSize(true)
        binding.recyclerFirst.layoutManager=LinearLayoutManager(context)

        catAdapter=CategoryAdapter(categoryList,this)
        binding.recyclerFirst.adapter=catAdapter

        getList()
        getMain()

    }
    //get list from viewModel
    private fun getList() {
        viewModel.getCategoryList().observe(viewLifecycleOwner, Observer {
            categoryList.clear()
            categoryList.addAll(it)
            catAdapter.notifyDataSetChanged()
        })
    }
    private fun getMain() {
        progrss=requireActivity().findViewById(R.id.progressBarMain)
        txtview=requireActivity().findViewById(R.id.textMain)
        txtview.setText("Satis İslemi(1/5)")
        progrss.setProgress(20)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "İlan Ver-Kategori Seç"
    }

    override fun onItemClick(position: Int) {
        viewModel.setSelectedCategory(position)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
    }
}