package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.Deal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DealItemViewModel(productId: Int) : ViewModel() {

    //TODO Implement dependency injection so we don't need to recreate repo
    private val dealsRepository: DealsRepository = DealsRepository()

    private val _deal: MutableStateFlow<Deal?> = MutableStateFlow(null)
    var deal: StateFlow<Deal?> = _deal.asStateFlow()


    init {
        viewModelScope.launch {
            _deal.value = dealsRepository.getDealInfo(productId.toString())
        }
    }

    //Use factory pattern to create instance of DealItemViewModel with productId as argument
    class DealItemViewModelFactory(
        private val productId: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DealItemViewModel(productId) as T
        }
    }

}