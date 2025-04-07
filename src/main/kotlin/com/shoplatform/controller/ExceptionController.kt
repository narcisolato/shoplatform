package com.shoplatform.controller

import com.shoplatform.dto.ExceptionDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ExceptionDto {
        return ExceptionDto.of(e)
    }
}