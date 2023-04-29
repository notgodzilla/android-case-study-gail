package com.target.targetcasestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*      "sale_price": {
        "amount_in_cents": 15999,
        "currency_symbol": "$",
        "display_string": "$159.99"
      },*/


@JsonClass(generateAdapter = true)
data class SalePrice(
    @Json(name = "amount_in_cents") val amountInCents: Int,
    @Json(name = "currency_symbol") val currencySymbol: String,
    @Json(name = "display_string") val displayString: String

)
