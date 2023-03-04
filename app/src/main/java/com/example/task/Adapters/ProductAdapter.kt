package com.example.task.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.task.Models.Products
import com.example.task.Models.SubCategory
import com.example.task.View.SecondFragmentDirections
import com.example.task.View.ThirdFragmentDirections
import com.example.task.databinding.SecondItemrowBinding
import com.example.task.databinding.ThirdItemrowBinding


class ProductAdapter(val productList:List<Products>) : RecyclerView.Adapter<ProductAdapter.CardHolder>() {

    class CardHolder(val binding : ThirdItemrowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val binding= ThirdItemrowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.thirdText.text=productList.get(position).product_ad
        holder.itemView.setOnClickListener {
            val action= ThirdFragmentDirections.actionThirdFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}