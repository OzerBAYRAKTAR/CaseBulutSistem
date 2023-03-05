package com.example.task.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.Models.SubCategory
import com.example.task.databinding.SecondItemrowBinding


class SubCategoryAdapter(val subKategoriList:List<SubCategory>,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SubCategoryAdapter.CardHolder>() {

    class CardHolder(val binding : SecondItemrowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val binding= SecondItemrowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.secondText.text=subKategoriList.get(position).subKategori_ad

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return subKategoriList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}