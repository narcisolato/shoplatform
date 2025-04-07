package com.shoplatform.dto.management

class ManagementDto {

    data class Response(
        val shop: ShopDto.Shop,
        val categoryList: List<CategoryDto.Category>,
        val itemList: List<ItemDto.Item>,
        val optionList: List<OptionDto.Option>,
        val optionValueList: List<OptionValueDto.OptionValue>
    )
}