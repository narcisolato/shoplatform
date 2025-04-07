package com.shoplatform.service.management

import com.shoplatform.dto.management.ItemDto
import com.shoplatform.entity.ItemEntity
import com.shoplatform.entity.ShopEntity
import com.shoplatform.repository.CategoryRepository
import com.shoplatform.repository.ItemRepository
import com.shoplatform.shared.converter.toDomain
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val shopService: ShopService,
    private val categoryRepository: CategoryRepository,
    private val itemRepository: ItemRepository,
) {
    // POST 상품 등록
    @Transactional
    fun create(request: ItemDto.Request): ItemDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val itemCodeList = request.itemList.map { it.code }

        if (existsAllItemEntity(shopEntity, itemCodeList)) {
            throw IllegalArgumentException("The Category list with codes: $itemCodeList exists")
        }

        val categoryCodeList = request.itemList.mapNotNull { it.categoryCode }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)
            .associateBy { it.code }

        val itemEntityList = request.itemList.map {
            val categoryEntity = categoryEntityMap[it.categoryCode]
                ?: throw IllegalArgumentException("Category with code ${it.categoryCode} not found")

            val itemEntity = ItemEntity.of(shopEntity, it.code)

            itemEntity.category = categoryEntity
            itemEntity.name = it.name
            itemEntity.price = it.price
            itemEntity
        }

        itemRepository.saveAll(itemEntityList)

        return ItemDto.Response(
            itemList = itemEntityList.map { it.toDomain() }
        )
    }

    @Transactional(readOnly = true)
    fun read(shopCode: String): ItemDto.Response {
        val shopEntity = shopService.getShopEntity(shopCode)
        val itemEntityList = shopEntity.itemList
        return ItemDto.Response(
            itemList = itemEntityList.map { it.toDomain() }
        )
    }

    // PATCH 상품 수정
    @Transactional
    fun update(request: ItemDto.Request): ItemDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val itemCodeList = request.itemList.map { it.code }
        val itemEntityMap = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
            .associateBy { it.code }

        val categoryCodeList = request.itemList.mapNotNull { it.categoryCode }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)
            .associateBy { it.code }

        val itemEntityList = request.itemList.map {
            val categoryEntity = categoryEntityMap[it.categoryCode]
                ?: throw IllegalArgumentException("Category with code ${it.categoryCode} not found")

            val itemEntity = itemEntityMap[it.code]
                ?: throw IllegalArgumentException("The Item with code: ${it.code} not found")

            itemEntity.category = categoryEntity
            itemEntity.name = it.name
            itemEntity.price = it.price
            itemEntity
        }
        itemRepository.saveAll(itemEntityList)

        return ItemDto.Response(
            itemList = itemEntityList.map { it.toDomain() }
        )
    }

    // PUT 상품 등록+수정
    @Transactional
    fun upsert(request: ItemDto.Request): ItemDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val itemCodeList = request.itemList.map { it.code }
        val itemEntityMap = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
            .associateBy { it.code }

        val categoryCodeList = request.itemList.mapNotNull { it.categoryCode }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)
            .associateBy { it.code }

        val itemEntityList = request.itemList.map {
            val categoryEntity = categoryEntityMap[it.categoryCode]
                ?: throw IllegalArgumentException("Category with code ${it.categoryCode} not found")

            val itemEntity = itemEntityMap[it.code]
                ?: ItemEntity.of(shopEntity, it.code)

            itemEntity.category = categoryEntity
            itemEntity.name = it.name
            itemEntity.price = it.price
            itemEntity
        }
        itemRepository.saveAll(itemEntityList)

        return ItemDto.Response(
            itemList = itemEntityList.map { it.toDomain() }
        )
    }

    // DELETE 상품 삭제
    @Transactional
    fun delete(request: ItemDto.Request) {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val itemCodeList = request.itemList.flatMap { listOf(it.code) }
        val itemEntityList = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
        itemRepository.deleteAll(itemEntityList)
    }

    private fun existsAllItemEntity(shopEntity: ShopEntity, codeList: List<String>)
            = itemRepository.existsAllByShopAndCodeIn(shopEntity, codeList)
}