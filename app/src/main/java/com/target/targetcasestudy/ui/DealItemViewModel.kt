package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.Deal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealItemViewModel @Inject constructor(
    private val dealsRepository: DealsRepository
) : ViewModel() {

    private val _deal: MutableStateFlow<Deal?> = MutableStateFlow(null)
    var deal: StateFlow<Deal?> = _deal.asStateFlow()

    suspend fun getDealInfo(productId: String) {
        viewModelScope.launch {
            _deal.value = dealsRepository.getDealInfo(productId)
        }

    }


}