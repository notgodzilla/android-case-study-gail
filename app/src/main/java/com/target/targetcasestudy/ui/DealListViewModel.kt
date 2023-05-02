package com.target.targetcasestudy.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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


    suspend fun getDeals() {
        viewModelScope.launch {
            try {
                val items = dealsRepository.getDeals()
                _dealItems.update { items }
            } catch (ex: Exception) {
                _dealItems.update { emptyList() }
                Log.e(TAG, "Failed to fetch deals", ex)
            }
        }

    }


}