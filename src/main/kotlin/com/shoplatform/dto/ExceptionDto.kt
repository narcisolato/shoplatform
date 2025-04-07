package com.shoplatform.dto

import com.shoplatform.common.logger

data class ExceptionDto(
    val type: String,
    val message: String,
) {
    companion object {
        private val log = logger()
        fun of(e: Exception): ExceptionDto {
            val exceptionDto = ExceptionDto(
                type = e.javaClass.simpleName,
                message = e.localizedMessage
            )
            log.info("{} occurred. {}", exceptionDto.type, exceptionDto.message)
            return exceptionDto
        }
    }
}
