package com.github.thiagodutra.api.dto

import com.github.thiagodutra.domain.model.Address
import com.github.thiagodutra.domain.model.Project

data class ProfessorDTO(
    var id: Long,
    var code: String,
    var name: String,
    var course: String,
    var address: Address,
    var projects: Project
)
