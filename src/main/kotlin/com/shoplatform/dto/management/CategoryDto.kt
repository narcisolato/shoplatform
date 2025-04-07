package com.shoplatform.dto.management

class CategoryDto {
    data class Category(
        val code: String,
        val name: String?,
        val parentCategoryCode: String?,
    )

    data class Request(
        val shopCode: String,
        val categoryList: List<Category>
    )

    // TODO CQS에 따라 POST 요청의 Response 모두 없애는 게 나을듯
    data class Response(
        val categoryList: List<Category>
    )
}