package com.shoplatform.service.management

import com.shoplatform.dto.management.OptionDto
import com.shoplatform.entity.OptionEntity
import com.shoplatform.entity.ShopEntity
import com.shoplatform.repository.ItemRepository
import com.shoplatform.repository.OptionRepository
import com.shoplatform.shared.converter.toDomain
import com.shoplatform.shared.updater.update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptionService(
    private val shopService: ShopService,
    private val itemRepository: ItemRepository,
    private val optionRepository: OptionRepository,
) {
    // POST 상품 등록
    @Transactional
    fun create(request: OptionDto.Request): OptionDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val optionCodeList = request.optionList.map { it.code }

        if (existsAllOptionEntity(shopEntity, optionCodeList)) {
            throw IllegalArgumentException("The Category list with codes: $optionCodeList exists")
        }

        val itemCodeList = request.optionList.mapNotNull { it.itemCode }
        val itemEntityMap = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
            .associateBy { it.code }

        val optionEntityList = request.optionList.map {
            val itemEntity = itemEntityMap[it.itemCode]
            val optionEntity = OptionEntity.of(shopEntity, it.code)

            optionEntity.update(it, itemEntity)
        }

        optionRepository.saveAll(optionEntityList)

        return OptionDto.Response(
            optionList = optionEntityList.map { it.toDomain() }
        )
    }

    // GET 상품 조회
    @Transactional(readOnly = true)
    fun read(shopCode: String): OptionDto.Response {
        val shopEntity = shopService.getShopEntity(shopCode)
        val optionEntityList = shopEntity.optionList
        return OptionDto.Response(
            optionList = optionEntityList.map { it.toDomain() }
        )
    }

    // PATCH 상품 수정
    @Transactional
    fun update(request: OptionDto.Request): OptionDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val optionCodeList = request.optionList.map { it.code }
        val optionEntityMap = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
            .associateBy { it.code }

        val itemCodeList = request.optionList.mapNotNull { it.itemCode }
        val itemEntityMap = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
            .associateBy { it.code }

        val optionEntityList = request.optionList.map {
            val itemEntity = itemEntityMap[it.itemCode]
            val optionEntity = optionEntityMap[it.code]
                ?: throw IllegalArgumentException("The option with code ${it.code} not found")

            optionEntity.update(it, itemEntity)
        }

        optionRepository.saveAll(optionEntityList)

        return OptionDto.Response(
            optionList = optionEntityList.map { it.toDomain()}
        )
    }

    // PUT 옵션 등록+수정
    @Transactional
    fun upsert(request: OptionDto.Request): OptionDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val optionCodeList = request.optionList.flatMap { listOf(it.code) }
        val optionEntityMap = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
            .associateBy { it.code }

        val itemCodeList = request.optionList.mapNotNull { it.itemCode }
        val itemEntityMap = itemRepository.findByShopAndCodeIn(shopEntity, itemCodeList)
            .associateBy { it.code }

        val optionEntityList = request.optionList.map {
            val itemEntity = itemEntityMap[it.itemCode]
            val optionEntity = optionEntityMap[it.code]
                ?: OptionEntity.of(shopEntity, it.code)

            optionEntity.update(it, itemEntity)
        }
        optionRepository.saveAll(optionEntityList)

        return OptionDto.Response(
            optionList = optionEntityList.map { it.toDomain() }
        )
    }

    // DELETE 옵션 삭제
    @Transactional
    fun delete(request: OptionDto.Request) {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val optionCodeList = request.optionList.flatMap { listOf(it.code) }
        val optionEntityList = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
        optionRepository.deleteAll(optionEntityList)
    }

    private fun existsAllOptionEntity(shopEntity: ShopEntity, codeList: List<String>)
            = optionRepository.existsAllByShopAndCodeIn(shopEntity, codeList)
}