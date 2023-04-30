package com.target.targetcasestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Deal(
    val id: Int,
    val title: String,
    val aisle: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "regular_price") val regularPrice: RegularPrice,
    @Json(name = "sale_price") val salePrice: SalePrice?,
    val fulfillment: String,
    val availability: String


)
