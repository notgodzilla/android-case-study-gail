package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.model.ItemNotFoundResponse
import com.target.targetcasestudy.model.getErrorResponse
import com.target.targetcasestudy.state.DealItemUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealItemViewModel @Inject constructor(
    private val dealsRepository: DealsRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<DealItemUIState> = MutableStateFlow(DealItemUIState())
    val dealItemUIState: StateFlow<DealItemUIState>
        get() = _uiState.asStateFlow()

    //Exception handler to keep track of UI state
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.update {
            it.copy(
                error = true
            )
        }
    }

    suspend fun getDealInfo(productId: String) {
        viewModelScope.launch(exceptionHandler) {
            try {
                val product = dealsRepository.getDealInfo(productId)
                _uiState.update {
                    it.copy(
                        product = product,
                        error = false
                    )
                }
            } catch (ex: Exception) {
                ex.getErrorResponse(404, ItemNotFoundResponse::class.java)
                    ?.let { itemNotFoundResponse ->
                        _uiState.update {
                            it.copy(
                                product = null,
                                error = true,
                                errorCode = itemNotFoundResponse.code,
                                errorMessage = itemNotFoundResponse.message
                            )
                        }
                    }
            }
        }
    }


}