package com.github.thiagodutra.api.dto

import java.util.*

sealed class DefaultResponse{
    class Success (val operation: String, val status: String, val payload: Optional<*>): DefaultResponse()
    class Error (val operation: String, val error: String, val description: String): DefaultResponse()
}
