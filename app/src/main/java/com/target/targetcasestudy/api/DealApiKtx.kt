package com.target.targetcasestudy.api

import com.target.targetcasestudy.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

internal const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"

interface DealApiKtx {

  @GET("${BASE_URL}deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("${BASE_URL}deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): Product
}
