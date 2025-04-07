package com.shoplatform.shared.converter

import com.shoplatform.dto.client.ClientDto
import com.shoplatform.entity.*

fun ShopEntity.toMenu() = ClientDto.Response(
    shop = ClientDto.Shop(
        code = code,
        name = name,
    ),
    categoryList = categoryList
        .filter { it.parentCategory == null }
        .map { it.toMenu() }
)

fun CategoryEntity.toMenu() : ClientDto.Category {
    return ClientDto.Category(
        code = code,
        name = name,
        itemList = itemList.map { it.toMenu() },
        childCategoryList = childCategoryList.map { it.toMenu() },
    )
}

fun ItemEntity.toMenu() = ClientDto.Item(
    code = code,
    name = name,
    price = price,
    optionList = optionList.map { it.toMenu() },
)

fun OptionEntity.toMenu() = ClientDto.Option(
    code = code,
    name = name,
    type = type?.name,
    optionValueList = valueList.map { it.toMenu() },
)

fun OptionValueEntity.toMenu() = ClientDto.OptionValue(
    code = code,
    name = name,
    price = price
)
