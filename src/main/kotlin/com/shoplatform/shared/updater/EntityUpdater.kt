package com.shoplatform.shared.updater

import com.shoplatform.dto.management.CategoryDto
import com.shoplatform.entity.CategoryEntity

fun CategoryEntity.update(category: CategoryDto.Category, parentCategoryEntity: CategoryEntity?): CategoryEntity {
    parentCategory = parentCategoryEntity
    name = category.name
    return this
}