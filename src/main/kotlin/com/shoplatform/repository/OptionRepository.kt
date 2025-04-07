package com.shoplatform.repository

import com.shoplatform.entity.OptionEntity
import com.shoplatform.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository : JpaRepository<OptionEntity, Long> {

    fun findByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): List<OptionEntity>

    fun existsAllByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): Boolean
}