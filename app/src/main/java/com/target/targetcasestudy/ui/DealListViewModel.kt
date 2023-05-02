package com.target.targetcasestudy.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.state.DealItemListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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


    private var _uiState: MutableStateFlow<DealItemListUIState> =
        MutableStateFlow(DealItemListUIState())
    val dealsListUIState: StateFlow<DealItemListUIState>
        get() = _uiState.asStateFlow()

    //Exception handler to keep track of UI state
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.update {
            it.copy(
                products = emptyList(),
                error = true
            )
        }
    }

    suspend fun getDeals() {
        viewModelScope.launch(exceptionHandler) {
            try {
                val items = dealsRepository.getDeals()
                _uiState.update {
                    it.copy(
                        products = items,
                        error = false
                    )
                }
            } catch (ex: Exception) {
                _uiState.update {
                    it.copy(
                        products = emptyList(),
                        error = true
                    )
                }
                Log.e(TAG, "Failed to fetch deals", ex)
            }
        }

    }


}