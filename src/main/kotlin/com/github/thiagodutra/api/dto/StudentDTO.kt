package com.github.thiagodutra.api.dto

import com.github.thiagodutra.domain.model.Address
import com.github.thiagodutra.domain.model.Project

data class StudentDTO(
    val id: Long,
    val name: String,
    val code: String,
    val cpf: String,
    val course: String,
    val address: Address,
    val projects: List<Project> = listOf<Project>()
)
