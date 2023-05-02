package com.target.targetcasestudy.ui

import com.target.targetcasestudy.model.Product

data class DealItemUIState(
    val product: Product? = null,
    val error: Boolean = false,
    val errorCode: String? = null,
    val errorMessage: String? = null
)