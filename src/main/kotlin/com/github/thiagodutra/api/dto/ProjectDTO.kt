package com.github.thiagodutra.api.dto

import com.github.thiagodutra.domain.model.Professor
import com.github.thiagodutra.domain.model.Student

data class ProjectDTO(
    var id: Long,
    var title: String,
    var field: String,
    var abstract: String,
    var firstKeyword: String,
    var secondKeyword: String,
    var thirdKeyword: String,
    var url: String,
    var professors: Professor,
    var students: MutableSet<Student> = mutableSetOf<Student>()
)
