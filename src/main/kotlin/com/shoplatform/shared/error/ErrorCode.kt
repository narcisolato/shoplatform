package com.shoplatform.shared.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {
    // TODO 에러 메시지 국제화
    EXISTS_SHOP(HttpStatus.BAD_REQUEST, "The shop already exists"),
    NOT_EXISTS_SHOP(HttpStatus.NOT_FOUND, "The shop not found"),
}