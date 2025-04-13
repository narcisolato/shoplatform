package com.shoplatform.controller

import ch.qos.logback.core.net.server.Client
import com.shoplatform.dto.ExceptionDto
import com.shoplatform.shared.error.ClientException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ExceptionDto {
        return ExceptionDto.from(e)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientException::class)
    fun handleClientException(e: ClientException): ExceptionDto {
        return ExceptionDto.from(e)
    }
}