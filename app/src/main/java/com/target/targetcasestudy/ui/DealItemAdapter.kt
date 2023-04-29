package com.target.targetcasestudy.ui

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.model.Product

class DealItemAdapter(private val deals: List<Product>) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Using viewBinding to bind fragment_deal_item to DealItemViewHolder
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding)
    }

    override fun getItemCount(): Int = deals.size


    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.bind(item)

    }
}

// Using viewBinding to bind fragment_deal_item to DealItemViewHolder
class DealItemViewHolder(private val binding: DealListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(deal: Product) {
        binding.dealListItemPrice.text = deal.regularPrice.displayString
        binding.dealListItemTitle.text = deal.title

    }


}

