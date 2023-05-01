package com.target.targetcasestudy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.model.Product

class DealItemAdapter(
    private val deals: List<Product>,
    private val context: Context,
    private val onProductClicked: (productId: Int) -> Unit
) :
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
        viewHolder.bind(item) {
            onProductClicked(item.id)
        }
    }
}

// Using viewBinding to bind fragment_deal_item to DealItemViewHolder
class DealItemViewHolder(
    private val binding: DealListItemBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product, onProductClicked: (productId: Int) -> Unit) {

        //Pass the product's id to DealListFragment
        binding.root.setOnClickListener { onProductClicked(product.id) }
        binding.dealListDisplayPrice.text = product.regularPrice.displayString

        if (product.salePrice != null) {
            binding.dealListDisplayPrice.text = product.salePrice.displayString
            binding.dealListSalePrice.text =
                context.getString(R.string.regular_price_text, product.regularPrice.displayString)
        } else {
            binding.dealListSalePrice.visibility = View.GONE
        }


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

