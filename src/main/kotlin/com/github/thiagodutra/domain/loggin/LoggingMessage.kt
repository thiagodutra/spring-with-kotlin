package com.github.thiagodutra.domain.loggin

import java.lang.Exception

class LoggingMessage (
    val message: String,
    val error: String,
    val exception: Exception?
    )    {
}