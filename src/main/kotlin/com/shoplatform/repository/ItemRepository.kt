package com.shoplatform.repository

import com.shoplatform.entity.ItemEntity
import com.shoplatform.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<ItemEntity, Long> {

    fun findByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): List<ItemEntity>

    fun existsAllByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): Boolean
}