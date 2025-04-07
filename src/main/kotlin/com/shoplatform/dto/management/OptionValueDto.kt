package com.shoplatform.dto.management

class OptionValueDto {
    data class OptionValue(
        val code: String,
        val optionCode: String?,
        val name: String?,
        val price: Double?,
    )

    data class Request(
        val shopCode: String,
        val optionValueList: List<OptionValue>,
    )

    data class Response(
        val optionValueList: List<OptionValue>,
    )
}