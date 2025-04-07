package com.shoplatform.dto.management

import com.shoplatform.shared.enums.OptionType

class OptionDto {
    data class Option(
        val code: String,
        val itemCode: String?,
        val name: String?,
        val type: OptionType?,
    )

    data class Request(
        val shopCode: String,
        val optionList: List<Option>
    )

    data class Response(
        val optionList: List<Option>
    )
}