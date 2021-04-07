package com.github.thiagodutra.domain.repository

import com.github.thiagodutra.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: JpaRepository<Student, Long> {
}