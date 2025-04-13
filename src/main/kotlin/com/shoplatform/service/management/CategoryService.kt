package com.shoplatform.service.management

import com.shoplatform.dto.management.CategoryDto
import com.shoplatform.entity.CategoryEntity
import com.shoplatform.entity.ShopEntity
import com.shoplatform.repository.CategoryRepository
import com.shoplatform.shared.converter.toDomain
import com.shoplatform.shared.updater.update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val shopService: ShopService,
    private val categoryRepository: CategoryRepository
) {
    // POST 카테고리 등록
    @Transactional
    fun create(request: CategoryDto.Request): CategoryDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val categoryCodeList = request.categoryList.map { it.code }

        if (existsAllCategoryEntity(shopEntity, categoryCodeList)) {
            throw IllegalArgumentException("The Category list with codes: $categoryCodeList exists")
        }

        val parentCategoryCodeList = request.categoryList.mapNotNull { it.parentCategoryCode }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, parentCategoryCodeList)
            .associateBy { it.code }
            .toMutableMap()

        val categoryEntityList = request.categoryList.map {
            val parentCategoryEntity = categoryEntityMap[it.parentCategoryCode]
            val categoryEntity = CategoryEntity.of(shopEntity, it.code)
            categoryEntityMap[it.code] = categoryEntity
            categoryEntity.update(it, parentCategoryEntity)
        }
        categoryRepository.saveAll(categoryEntityList)

        return CategoryDto.Response(categoryEntityList.map { it.toDomain() })
    }

    // GET 카테고리 조회
    @Transactional(readOnly = true)
    fun read(shopCode: String): CategoryDto.Response {
        val shopEntity = shopService.getShopEntity(shopCode)
        val categoryEntityList = shopEntity.categoryList
        return CategoryDto.Response(categoryEntityList.map { it.toDomain() })
    }

    // PATCH 카테고리 수정
    @Transactional
    fun update(request: CategoryDto.Request): CategoryDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val categoryCodeList = request.categoryList.flatMap { listOfNotNull(it.code, it.parentCategoryCode) }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)
            .associateBy { it.code }
            .toMutableMap()

        val categoryEntityList = request.categoryList.map {
            val parentCategoryEntity = categoryEntityMap[it.parentCategoryCode]
            val categoryEntity = categoryEntityMap[it.code]
                ?: throw IllegalArgumentException("The Category with code: ${it.code} not found")

            categoryEntity.update(it, parentCategoryEntity)
        }
        categoryRepository.saveAll(categoryEntityList)

        return CategoryDto.Response(categoryEntityList.map { it.toDomain() })
    }

    // PUT 카테고리 등록+수정
    @Transactional
    fun upsert(request: CategoryDto.Request): CategoryDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val categoryCodeList = request.categoryList.flatMap { listOfNotNull(it.code, it.parentCategoryCode) }
        val categoryEntityMap = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)
            .associateBy { it.code }
            .toMutableMap()

        val categoryEntityList = request.categoryList.map {
            val parentCategoryEntity = categoryEntityMap[it.parentCategoryCode]
            val categoryEntity = categoryEntityMap[it.code]
                ?: CategoryEntity.of(shopEntity, it.code)
            categoryEntityMap[it.code] = categoryEntity

            categoryEntity.update(it, parentCategoryEntity)
        }
        categoryRepository.saveAll(categoryEntityList)

        return CategoryDto.Response(categoryEntityList.map { it.toDomain() })
    }

    // DELETE 카테고리 삭제
    @Transactional
    fun delete(request: CategoryDto.Request) {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val categoryCodeList = request.categoryList.flatMap { listOf(it.code) }
        val categoryEntityList = categoryRepository.findByShopAndCodeIn(shopEntity, categoryCodeList)

        categoryRepository.deleteAll(categoryEntityList)
    }

    private fun existsAllCategoryEntity(shopEntity: ShopEntity, codeList: List<String>)
        = categoryRepository.existsAllByShopAndCodeIn(shopEntity, codeList)
}