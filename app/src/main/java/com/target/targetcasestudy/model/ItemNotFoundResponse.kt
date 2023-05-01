package com.target.targetcasestudy.model

import com.google.gson.Gson
import com.squareup.moshi.JsonClass
import retrofit2.HttpException

@JsonClass(generateAdapter = true)
data class ItemNotFoundResponse(
    val code: String,
    val message: String
)

//Get errorBody from ItemNotFoundResponse
fun <T> Exception.getErrorResponse(httpCode: Int, clazz: Class<T>): T? {
    return if (this is HttpException && code() == httpCode) {
        try {
            Gson().fromJson(response()?.errorBody()?.string(), clazz)
        } catch (ex: Exception) {
            null
        }
    } else {
        null
    }
}