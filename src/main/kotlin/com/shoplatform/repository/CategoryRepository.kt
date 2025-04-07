package com.shoplatform.repository

import com.shoplatform.entity.CategoryEntity
import com.shoplatform.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {

    fun findByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): List<CategoryEntity>

    fun existsAllByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): Boolean
}