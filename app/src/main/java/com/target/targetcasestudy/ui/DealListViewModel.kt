package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import kotlinx.coroutines.launch

class DealListViewModel : ViewModel() {

    private val dealsRepository: DealsRepository = DealsRepository()

    //TODO Call dealsRepository in viewModel
    fun getDeals() {
        viewModelScope.launch {
            dealsRepository.getDeals()
        }
    }
}