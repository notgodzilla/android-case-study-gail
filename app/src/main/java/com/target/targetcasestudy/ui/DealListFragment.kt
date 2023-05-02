package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import kotlinx.coroutines.launch


class DealListFragment : Fragment() {

    private var _binding: FragmentDealListBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    private val dealListViewModel: DealListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDealListBinding.inflate(inflater, container, false)
        binding.dealListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.dealListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                //Handle fetching / refreshing deals state
                binding.dealListRecyclerView.visibility = View.GONE
                hideErrorState()

                dealListViewModel.dealsListUIState.collect { productsState ->
                    if (productsState.loading) {
                        binding.dealListRecyclerView.visibility = View.GONE
                        binding.dealsListProgressBar.visibility = View.VISIBLE
                    } else {
                        binding.dealsListProgressBar.visibility = View.GONE
                    }
                    if (productsState.error) {
                        showErrorState()
                    } else {
                        hideErrorState()
                        binding.dealListRecyclerView.visibility = View.VISIBLE
                        binding.dealListRecyclerView.adapter =
                            DealItemAdapter(productsState.products, requireContext()) { productId ->
                                //Product id from ViewHolder, passed into onProductClicked listener
                                onProductClicked(productId)
                            }
                    }
                }
            }
        }
    }

    private fun hideErrorState() {
        binding.dealsListErrorIcon.visibility = View.GONE
        binding.dealsListErrorText.visibility = View.GONE
        binding.dealsListErrorRefreshButton.visibility = View.GONE
    }

    private fun showErrorState() {
        binding.dealsListErrorIcon.visibility = View.VISIBLE
        binding.dealsListErrorText.visibility = View.VISIBLE
        binding.dealsListErrorRefreshButton.visibility = View.VISIBLE
        binding.dealsListErrorRefreshButton.setOnClickListener {
            dealListViewModel.getDeals()
        }
    }

    //Navigates to DealItemFragment from given productId
    private fun onProductClicked(productId: Int) {
        findNavController().navigate(DealListFragmentDirections.showDealItemDetails(productId))
    }
}
