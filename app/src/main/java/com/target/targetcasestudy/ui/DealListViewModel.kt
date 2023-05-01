package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(
    private val dealsRepository: DealsRepository
) : ViewModel() {

    private val _dealItems: MutableStateFlow<List<Product>> =
        MutableStateFlow(emptyList())
    val dealItems: StateFlow<List<Product>>
        get() = _dealItems.asStateFlow()


    init {
        viewModelScope.launch {
            val items = dealsRepository.getDeals()
            _dealItems.value = items
        }
    }


}