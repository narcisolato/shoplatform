package com.shoplatform.service.management

import com.shoplatform.dto.management.ManagementDto
import com.shoplatform.entity.CategoryEntity
import com.shoplatform.entity.ItemEntity
import com.shoplatform.entity.OptionEntity
import com.shoplatform.entity.OptionValueEntity
import com.shoplatform.shared.converter.toDomain
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagementService(
    private val shopService: ShopService,
) {
    // 전체 매장 정보 조회
    @Transactional(readOnly = true)
    fun read(code: String): ManagementDto.Response {
        val shopEntity = shopService.getShopEntity(code)

        val shop = shopEntity.toDomain()
        val categoryList = shopEntity.categoryList.map(CategoryEntity::toDomain)
        val itemList = shopEntity.itemList.map(ItemEntity::toDomain)
        val optionList = shopEntity.optionList.map(OptionEntity::toDomain)
        val optionValueList = shopEntity.optionValueList.map(OptionValueEntity::toDomain)

        return ManagementDto.Response(
            shop = shop,
            categoryList = categoryList,
            itemList = itemList,
            optionList = optionList,
            optionValueList = optionValueList,
        )
    }
}