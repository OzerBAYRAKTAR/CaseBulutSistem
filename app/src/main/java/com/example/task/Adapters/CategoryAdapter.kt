package com.example.task.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.task.Models.Category
import com.example.task.View.FirstFragmentDirections
import com.example.task.databinding.MainItemrowBinding

class CategoryAdapter(val kategoriList:List<Category> ,val onItemclickListener: OnItemClickListener) : RecyclerView.Adapter<CategoryAdapter.CardHolder>() {

    class CardHolder(val binding : MainItemrowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val binding=MainItemrowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.mainText.text=kategoriList.get(position).kategori_ad

        holder.itemView.setOnClickListener {
            onItemclickListener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
       return kategoriList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}