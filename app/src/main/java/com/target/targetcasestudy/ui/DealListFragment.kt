package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import kotlinx.coroutines.launch


class DealListFragment : Fragment() {

    private var _binding: FragmentDealListBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    //TODO Temporarily creating instance of DealsRepository in Fragment to test networking
    private val dealsRepository: DealsRepository = DealsRepository()


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

        //TODO Temporarily calling getDeals() in here to test networking
        viewLifecycleOwner.lifecycleScope.launch {
            val deals = dealsRepository.getDeals()
            binding.dealListRecyclerView.adapter = DealItemAdapter(deals, requireContext())
        }

    }
}
