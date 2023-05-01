package com.target.targetcasestudy.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemNotFoundResponse(
    val code: String,
    val message: String
)


