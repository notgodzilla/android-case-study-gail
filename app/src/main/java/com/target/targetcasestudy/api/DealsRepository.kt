package com.target.targetcasestudy.api

import com.target.targetcasestudy.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealsRepository @Inject constructor(private val dealsApi: DealApiKtx) {

    suspend fun getDeals(): List<Product> = dealsApi.retrieveDeals().deals

    suspend fun getDealInfo(productId: String): Product = dealsApi.retrieveDeal(productId)
}