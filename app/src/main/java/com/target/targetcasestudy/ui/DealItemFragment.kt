package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.target.targetcasestudy.databinding.FragmentDealItemBinding


class DealItemFragment : Fragment() {


    // Gets args from safeArgs passed by DealListFragment,
    private val args: DealItemFragmentArgs by navArgs()
    private var _binding: FragmentDealItemBinding? = null
    private val binding
        get() = checkNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDealItemBinding.inflate(inflater, container, false)
        binding.productDetailText.text = args.productId.toString()
        return binding.root
    }
}
