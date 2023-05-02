package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.model.Product
import kotlinx.coroutines.launch


class DealItemFragment : Fragment() {

    // Gets args from safeArgs passed by DealListFragment,
    private val args: DealItemFragmentArgs by navArgs()
    private var _binding: FragmentDealItemBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    private val dealItemViewModel: DealItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDealItemBinding.inflate(inflater, container, false)
        binding.dealDetailsBackArrow.setOnClickListener { findNavController().popBackStack() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dealItemViewModel.getDealInfo(args.productId.toString())
                dealItemViewModel.dealItemUIState.collect { state ->
                    if (state.error) {
                        showErrorState(state.errorMessage ?: getString(R.string.generic_error))
                    } else {
                        state.product?.let { showDealItemDetails(it) }
                    }
                }
            }
        }
    }

    //No real functionality other than adding the option to show an action when 'Add to cart' is clicked
    private fun onAddToCartClicked(product: Product) {
        Toast.makeText(
            requireContext(),
            requireContext().getString(R.string.added_to_cart, product.title),
            Toast.LENGTH_SHORT
        ).show()
    }

    // Hides layout and shows message from ItemNotFoundResponse message
    private fun showErrorState(errorText: String) {
        //Hides product description and add to cart button
        binding.deailProductDetailDescriptionCard.visibility = View.GONE
        binding.detailDetailAddToCartCard.visibility = View.GONE

        //Set product photo to show empty cart and set product title to error message
        binding.dealDetailProductPhoto.setImageResource(R.drawable.ic_empty_cart)
        binding.dealDetailItemTitle.text = errorText
    }

    private fun showDealItemDetails(product: Product) {
        binding.deailProductDetailDescriptionCard.visibility = View.VISIBLE
        binding.detailDetailAddToCartCard.visibility = View.VISIBLE

        product.imageUrl.let {
            Glide.with(requireContext()).load(it)
                .apply(RequestOptions().transform(RoundedCorners(50)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.dealDetailProductPhoto)

        }
        binding.dealDetailItemTitle.text = product.title
        binding.dealDetailFulfillment.text = product.fulfillment
        binding.dealDetailProductDescription.text = product.description

        binding.dealDetailDisplayPrice.text = product.regularPrice.displayString
        if (product.salePrice != null) {
            binding.dealDetailDisplayPrice.text = product.salePrice.displayString
            binding.dealDetailSalePrice.text =
                requireContext().getString(
                    R.string.regular_price_text,
                    product.regularPrice.displayString
                )
        } else {
            binding.dealDetailSalePrice.visibility = View.GONE
        }
        binding.dealDetailProductDescription.text = product.description

        binding.dealDetailAddToCart.setOnClickListener { onAddToCartClicked(product) }


    }
}
