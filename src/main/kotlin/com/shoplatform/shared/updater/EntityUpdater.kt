package com.shoplatform.shared.updater

import com.shoplatform.dto.management.CategoryDto
import com.shoplatform.dto.management.ItemDto
import com.shoplatform.dto.management.OptionDto
import com.shoplatform.dto.management.OptionValueDto
import com.shoplatform.entity.CategoryEntity
import com.shoplatform.entity.ItemEntity
import com.shoplatform.entity.OptionEntity
import com.shoplatform.entity.OptionValueEntity

fun CategoryEntity.update(category: CategoryDto.Category, parentCategoryEntity: CategoryEntity?): CategoryEntity {
    parentCategory = parentCategoryEntity
    name = category.name
    return this
}

fun ItemEntity.update(item: ItemDto.Item, categoryEntity: CategoryEntity?): ItemEntity {
    category = categoryEntity
    name = item.name
    price = item.price
    return this
}

fun OptionEntity.update(option: OptionDto.Option, itemEntity: ItemEntity?): OptionEntity {
    item = itemEntity
    name = option.name
    type = option.type
    return this
}

fun OptionValueEntity.update(optionValue: OptionValueDto.OptionValue, optionEntity: OptionEntity?): OptionValueEntity {
    option = optionEntity
    name = optionValue.name
    price = optionValue.price
    return this
}