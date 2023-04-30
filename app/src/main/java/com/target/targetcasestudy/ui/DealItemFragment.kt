package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.model.Deal
import kotlinx.coroutines.launch


class DealItemFragment : Fragment() {

    // Gets args from safeArgs passed by DealListFragment,
    private val args: DealItemFragmentArgs by navArgs()
    private var _binding: FragmentDealItemBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    //Use args to create viewModel via factory pattern
    private val dealItemViewModel: DealItemViewModel by viewModels {
        DealItemViewModel.DealItemViewModelFactory(args.productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDealItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dealItemViewModel.deal.collect {
                    it?.let { showDealItemDetails(it) }

                }

            }
        }
    }

    private fun showDealItemDetails(deal: Deal) {
        deal.imageUrl.let {
            Glide.with(requireContext()).load(it)
                .apply(RequestOptions().transform(RoundedCorners(50)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.dealDetailProductPhoto)

        }
        binding.dealDetailItemTitle.text = deal.title
        binding.dealDetailFulfillment.text = deal.fulfillment
        binding.dealDetailProductDescription.text = deal.description
        binding.dealDetailRegularPrice.text = deal.regularPrice.displayString
        binding.dealDetailSalePrice.text =
            deal.salePrice?.displayString ?: deal.regularPrice.displayString
        binding.dealDetailProductDescription.text = deal.description


    }
}
