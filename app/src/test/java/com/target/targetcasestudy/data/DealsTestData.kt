package com.target.targetcasestudy.data

import com.target.targetcasestudy.model.ItemNotFoundResponse
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.RegularPrice
import com.target.targetcasestudy.model.SalePrice

val productsList = listOf(
    Product(
        0,
        "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV",
        "b2",
        "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/0",
        "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
        RegularPrice(
            20999,
            "$",
            "$209.99"
        ),
        SalePrice(
            15999,
            "$",
            "$159.99"
        ),
        "Online",
        "In Stock"
    ),
    Product(
        1,
        "TCL 32\" Class 3-Series HD Smart Roku TV",
        "g33",
        "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/1",
        "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
        RegularPrice(
            20999,
            "$",
            "$209.99"
        ),
        SalePrice(
            15999,
            "$",
            "$159.99"
        ),
        "Online",
        "In Stock"
    )
)

val productDetail =
    Product(
        0,
        "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV",
        "b2",
        "Versatility and performance collide with the new D-Series Full HD Smart TV that comes loaded with a full array backlight for better contrast and uniformity, brilliant 1080p Full HD resolution and an ultra-fast processor with support for immersive audio pass-through with Dolby Atmos and DTS:X. Game on with the V-Gaming Engine that enables next-level gaming performance with features like Variable Refresh Rate, auto game mode and lower latency gaming. D-Series blends power, speed and versatile size, while featuring VIZIO’s award-winning SmartCast platform. Enjoy the very best selection of built-in apps from top-tier streaming services combined with an unmatched user experience and interface. With Live TV and hundreds of free channels through Watchfree+ together with Apple Airplay 2 and Chromecast built-in, the D-Series offers unparalleled entertainment in a size that fits your lifestyle.",
        "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
        RegularPrice(
            22999,
            "$",
            "$229.99"
        ),
        null,
        "Online",
        "In stock"
    )

val itemNotFoundResponse =
    ItemNotFoundResponse(
        "ITEM_NOT_FOUND",
        "product (50) is no longer available",
    )