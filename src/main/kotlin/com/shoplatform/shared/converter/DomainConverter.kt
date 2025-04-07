package com.shoplatform.shared.converter

import com.shoplatform.dto.management.*
import com.shoplatform.entity.*

fun ShopEntity.toDomain() = ShopDto.Shop(
    code = this.code,
    name = this.name,
)

fun CategoryEntity.toDomain() = CategoryDto.Category(
    code = this.code,
    name = this.name,
    parentCategoryCode = this.parentCategory?.code
)

fun ItemEntity.toDomain() = ItemDto.Item(
    code = this.code,
    categoryCode = this.category?.code,
    name = this.name,
    price = this.price
)

fun OptionEntity.toDomain() = OptionDto.Option(
    code = this.code,
    itemCode = this.item?.code,
    name = this.name,
    type = this.type,
)

fun OptionValueEntity.toDomain() = OptionValueDto.OptionValue(
    code = this.code,
    optionCode = this.option?.code,
    name = this.name,
    price = this.price,
)