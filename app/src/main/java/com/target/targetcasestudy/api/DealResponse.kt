package com.target.targetcasestudy.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.target.targetcasestudy.model.Product

@JsonClass(generateAdapter = true)
data class DealResponse(
    @Json(name = "products")
    val deals: List<Product>
)
