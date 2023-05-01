package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.ItemNotFoundResponse
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.getErrorResponse
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

    private val _deal: MutableStateFlow<Product?> = MutableStateFlow(null)
    var deal: StateFlow<Product?> = _deal.asStateFlow()

    suspend fun getDealInfo(productId: String) {

        viewModelScope.launch {
            try {
                _deal.value = dealsRepository.getDealInfo(productId)
            } catch (ex: Exception) {
                ex.getErrorResponse(404, ItemNotFoundResponse::class.java)?.let {
                }
            }
        }
    }


}