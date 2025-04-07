package com.shoplatform.dto.client

class ClientDto {
    data class Response(
        val shop: Shop,
        val categoryList: List<Category>,
    )

    data class Shop(
        val code: String,
        val name: String?
    )

    data class Category(
        val code: String,
        val name: String?,
        val itemList: List<Item>,
        val childCategoryList: List<Category>
    )

    data class Item(
        val code: String,
        val name: String?,
        val price: Double?,
        val optionList: List<Option>,
    )

    data class Option(
        val code: String,
        val name: String?,
        val type: String?,
        val optionValueList: List<OptionValue>,
    )

    data class OptionValue(
        val code: String,
        val name: String?,
        val price: Double?,
    )
}