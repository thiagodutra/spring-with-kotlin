package com.github.thiagodutra.api.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AddressDTO(
    var id: Long?,
    @field:NotBlank
    @field:Size(min = 1, max = 80, message
    = "Street character size must be between 0 and 80")
    var street: String,
    @field:NotBlank
    @field:Size(min = 1, max = 6, message
    = "Number size must be between 0 and 6 characters long")
    var number: String,
    @field:NotBlank
    @field:Size(min = 8, max = 8, message
    = "Zipcode must be at least 8 characters long")
    var zipcode: String,
    @field:NotBlank
    @field:Size(min = 3, max = 50, message
    = "City must be at least 8 characters long")
    var city: String,
    @field:NotBlank
    @field:Size(min = 3, max = 50, message
    = "State must be at least 8 characters long")
    var state: String,
    @field:NotBlank
    @field:Size(min = 3, max = 50, message
    = "Country must be at least 8 characters long")
    var country: String
    )
