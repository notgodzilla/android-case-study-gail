package com.target.targetcasestudy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.model.Product

class DealItemAdapter(private val deals: List<Product>, private val context: Context) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Using viewBinding to bind fragment_deal_item to DealItemViewHolder
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding, context)
    }

    override fun getItemCount(): Int = deals.size


    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.bind(item)

    }
}

// Using viewBinding to bind fragment_deal_item to DealItemViewHolder
class DealItemViewHolder(private val binding: DealListItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.dealListRegularPrice.text = product.regularPrice.displayString

        binding.dealListSalePrice.text = context.getString(
            R.string.regular_price_text,
            product.salePrice?.displayString ?: product.regularPrice.displayString
        )
        binding.dealListItemDescription.text = product.title
        binding.dealListFulfillment.text = product.fulfillment
        binding.dealListAisleInfo.text = context.getString(
            R.string.in_aisle_text, product.aisle.uppercase()
        )
        binding.dealListAvailability.text = product.availability
        product.imageUrl?.let {

            Glide.with(context).load(it).apply(RequestOptions().transform(RoundedCorners(50)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.dealListProductPhoto)

        }


    }


}

