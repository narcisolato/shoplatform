package com.shoplatform.dto

import com.shoplatform.common.logger
import com.shoplatform.shared.error.ClientException

data class ExceptionDto(
    val type: String,
    val message: String,
) {
    companion object {
        private val log = logger()
        fun from(e: Exception): ExceptionDto {
            val exceptionDto = ExceptionDto(
                type = e.javaClass.simpleName,
                message = e.localizedMessage ?: "Unknown error",
            )
            log.info("General Exception occurred. Type: {}, Message: {}",
                exceptionDto.type, exceptionDto.message)
            return exceptionDto
        }

        fun from(e: ClientException): ExceptionDto {
            val exceptionDto = ExceptionDto(
                type = e.code.status.name,
                message = e.code.message,
            )
            log.info("ClientException occurred. Status: {}, Description: {}",
                e.code.status.name, e.description)
            return exceptionDto
        }
    }
}
