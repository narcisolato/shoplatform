package com.shoplatform.dto.management

class ShopDto {
    data class Shop(
        val code: String,
        val name: String?,
    )

    data class Request(
        val shop: Shop,
    )

    data class Response(
        val shop: Shop,
    )
}
