package com.github.thiagodutra.domain.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "Students")
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "cpf", nullable = false)
    val cpf: String,

    @Column(name = "course", nullable = false)
    val course: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    val address: Address,

    @Column(name = "project",)
    @OneToMany(cascade = [CascadeType.PERSIST])
    val projects: List<Project> = listOf<Project>()

)