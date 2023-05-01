package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
                dealListViewModel.dealItems.collect { products ->
                    binding.dealListRecyclerView.adapter =
                        DealItemAdapter(products, requireContext()) { productId ->
                            onProductClicked(productId)
                        }
                }

            }
        }

    }

    private fun onProductClicked(productId: Int) {
        findNavController().navigate(DealListFragmentDirections.showDealItemDetails(productId))
    }
}
