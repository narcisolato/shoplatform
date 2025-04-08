package com.shoplatform.shared.updater

import com.shoplatform.dto.management.CategoryDto
import com.shoplatform.dto.management.ItemDto
import com.shoplatform.entity.CategoryEntity
import com.shoplatform.entity.ItemEntity

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