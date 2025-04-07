package com.shoplatform.service.management


import com.shoplatform.dto.management.OptionValueDto
import com.shoplatform.entity.OptionValueEntity
import com.shoplatform.entity.ShopEntity
import com.shoplatform.repository.OptionRepository
import com.shoplatform.repository.OptionValueRepository
import com.shoplatform.shared.converter.toDomain
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptionValueService(
    private val shopService: ShopService,
    private val optionRepository: OptionRepository,
    private val optionValueRepository: OptionValueRepository,
) {
    // POST 옵션값 등록
    @Transactional
    fun create(request: OptionValueDto.Request): OptionValueDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val optionValueCodeList = request.optionValueList.map { it.code }

        if (existsAllOptionValueEntity(shopEntity, optionValueCodeList)) {
            throw IllegalArgumentException("The Category list with codes: $optionValueCodeList exists")
        }

        val optionCodeList = request.optionValueList.mapNotNull { it.optionCode }
        val optionEntityMap = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
            .associateBy { it.code }

        val optionValueEntityList = request.optionValueList.map {
            val optionEntity = optionEntityMap[it.optionCode]
                ?: throw IllegalArgumentException("The Option with code: ${it.optionCode} not exists")

            val optionValueEntity = OptionValueEntity.of(shopEntity, it.code)

            optionValueEntity.option = optionEntity
            optionValueEntity.name = it.name
            optionValueEntity.price = it.price
            optionValueEntity
        }

        optionValueRepository.saveAll(optionValueEntityList)

        return OptionValueDto.Response(
            optionValueList = optionValueEntityList.map { it.toDomain()}
        )
    }

    // GET 옵션값 조회
    @Transactional(readOnly = true)
    fun read(shopCode: String): OptionValueDto.Response {
        val shopEntity = shopService.getShopEntity(shopCode)
        val optionValueEntityList = shopEntity.optionValueList
        return OptionValueDto.Response(
            optionValueList = optionValueEntityList.map { it.toDomain() }
        )
    }

    // PATCH 옵션값 수정
    @Transactional
    fun update(request: OptionValueDto.Request): OptionValueDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val optionValueCodeList = request.optionValueList.map { it.code }
        val optionValueEntityMap = optionValueRepository.findByShopAndCodeIn(shopEntity, optionValueCodeList)
            .associateBy { it.code }

        val optionCodeList = request.optionValueList.mapNotNull { it.optionCode }
        val optionEntityMap = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
            .associateBy { it.code }

        val optionValueEntityList = request.optionValueList.map {
            val optionEntity = optionEntityMap[it.optionCode]
                ?: throw IllegalArgumentException("The Option with code: ${it.optionCode} not exists")

            val optionValueEntity = optionValueEntityMap[it.code]
                ?: throw IllegalArgumentException("The Option Value with code: ${it.code} not exists")

            optionValueEntity.option = optionEntity
            optionValueEntity.name = it.name
            optionValueEntity.price = it.price
            optionValueEntity
        }

        optionValueRepository.saveAll(optionValueEntityList)

        return OptionValueDto.Response(
            optionValueList = optionValueEntityList.map { it.toDomain()}
        )
    }

    // PUT 옵션값 등록+수정
    @Transactional
    fun upsert(request: OptionValueDto.Request): OptionValueDto.Response {
        val shopEntity = shopService.getShopEntity(request.shopCode)

        val optionValueCodeList = request.optionValueList.map { it.code }
        val optionValueEntityMap = optionValueRepository.findByShopAndCodeIn(shopEntity, optionValueCodeList)
            .associateBy { it.code }

        val optionCodeList = request.optionValueList.mapNotNull { it.optionCode }
        val optionEntityMap = optionRepository.findByShopAndCodeIn(shopEntity, optionCodeList)
            .associateBy { it.code }

        val optionValueEntityList = request.optionValueList.map {
            val optionEntity = optionEntityMap[it.optionCode]
                ?: throw IllegalArgumentException("Option with code ${it.optionCode} not found")

            val optionValueEntity = optionValueEntityMap[it.code]
                ?: OptionValueEntity.of(shopEntity, it.code)

            optionValueEntity.option = optionEntity
            optionValueEntity.name = it.name
            optionValueEntity.price = it.price
            optionValueEntity
        }
        optionValueRepository.saveAll(optionValueEntityList)

        return OptionValueDto.Response(
            optionValueList = optionValueEntityList.map { it.toDomain() }
        )
    }

    // DELETE 옵션값 삭제
    @Transactional
    fun delete(request: OptionValueDto.Request) {
        val shopEntity = shopService.getShopEntity(request.shopCode)
        val optionValueCodeList = request.optionValueList.flatMap { listOf(it.code) }
        val optionValueEntityList = optionValueRepository.findByShopAndCodeIn(shopEntity, optionValueCodeList)
        optionValueRepository.deleteAll(optionValueEntityList)
    }

    private fun existsAllOptionValueEntity(shopEntity: ShopEntity, codeList: List<String>)
            = optionValueRepository.existsAllByShopAndCodeIn(shopEntity, codeList)
}