package com.github.thiagodutra.domain.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "Professors")
data class Professor (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @Column(name = "code")
        var code: String,

        @Column(name = "name")
        var name: String,

        @Column(name = "course")
        var course: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @Fetch(FetchMode.JOIN)
        var address: Address,

        @OneToOne(mappedBy = "professors")
        var projects: Project
)