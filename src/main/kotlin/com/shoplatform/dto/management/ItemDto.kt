package com.shoplatform.dto.management

class ItemDto {
    data class Item(
        val code: String,
        val categoryCode: String?,
        val name: String?,
        val price: Double?,
    )

    data class Request(
        val shopCode: String,
        val itemList: List<Item>
    )

    data class Response(
        val itemList: List<Item>
    )
}