package com.shoplatform.repository

import com.shoplatform.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<ShopEntity, Long> {

    fun findByCode(code: String): ShopEntity?

    fun existsByCode(code: String): Boolean
}