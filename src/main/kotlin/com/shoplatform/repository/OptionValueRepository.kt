package com.shoplatform.repository

import com.shoplatform.entity.OptionValueEntity
import com.shoplatform.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionValueRepository : JpaRepository<OptionValueEntity, Long> {

    fun findByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): List<OptionValueEntity>

    fun existsAllByShopAndCodeIn(shop: ShopEntity, codes: Collection<String>): Boolean
}