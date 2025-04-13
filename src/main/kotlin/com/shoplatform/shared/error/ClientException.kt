package com.shoplatform.shared.error

class ClientException(
    val code: ErrorCode,
    val description: String? = null,
): RuntimeException()