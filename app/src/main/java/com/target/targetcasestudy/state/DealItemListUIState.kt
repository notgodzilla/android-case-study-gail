package com.target.targetcasestudy.state

import com.target.targetcasestudy.model.Product

data class DealItemListUIState(
    val products: List<Product> = emptyList(),
    val error: Boolean = false
)