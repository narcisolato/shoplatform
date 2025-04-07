package com.shoplatform.service.management

import com.shoplatform.dto.management.ShopDto
import com.shoplatform.entity.ShopEntity
import com.shoplatform.repository.ShopRepository
import com.shoplatform.shared.converter.toDomain
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopService(
    private val shopRepository: ShopRepository,
) {
    // POST 매장 등록
    @Transactional
    fun create(request: ShopDto.Request): ShopDto.Response {
        if (existsShopEntity(request.shop.code)) {
            throw IllegalArgumentException("The shop code: ${request.shop.code} already exists")
        }

        val shopEntity = ShopEntity.of(request.shop.code)

        shopEntity.name = request.shop.name
        shopRepository.save(shopEntity)

        return ShopDto.Response(
            shop = shopEntity.toDomain(),
        )
    }

    // GET 매장 조회
    @Transactional(readOnly = true)
    fun read(code: String): ShopDto.Response {
        val shopEntity = getShopEntity(code)

        return ShopDto.Response(
            shop = shopEntity.toDomain(),
        )
    }

    // PATCH 매장 수정
    @Transactional
    fun update(request: ShopDto.Request): ShopDto.Response {
        val shopEntity = getShopEntity(request.shop.code)
        shopEntity.name = request.shop.name

        return ShopDto.Response(
            shop = shopEntity.toDomain(),
        )
    }

    // PUT 매장 등록+수정
    @Transactional
    fun upsert(request: ShopDto.Request): ShopDto.Response {
        val shopEntity = shopRepository.findByCode(request.shop.code)
            ?: ShopEntity.of(request.shop.code)

        shopEntity.name = request.shop.name
        shopRepository.save(shopEntity)

        return ShopDto.Response(
            shop = shopEntity.toDomain(),
        )
    }

    // DELETE 매장 삭제
    @Transactional
    fun delete(request: ShopDto.Request) {
        val shopEntity = getShopEntity(request.shop.code)
        shopRepository.delete(shopEntity)
    }

    fun getShopEntity(code: String): ShopEntity {
        return shopRepository.findByCode(code)
            ?: throw IllegalArgumentException("The Shop with code: $code not found")
    }

    private fun existsShopEntity(code: String) = shopRepository.existsByCode(code)
}