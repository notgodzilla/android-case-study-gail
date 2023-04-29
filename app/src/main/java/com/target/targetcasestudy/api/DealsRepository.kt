package com.target.targetcasestudy.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.target.targetcasestudy.model.Product
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class DealsRepository {

    private val dealsApi: DealApiKtx

    init {
        //Create instance of Moshi for JSON converter
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        //Create instance of Retrofit for networking
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        dealsApi = retrofit.create()
    }

    suspend fun getDeals(): List<Product> = dealsApi.retrieveDeals().deals
}