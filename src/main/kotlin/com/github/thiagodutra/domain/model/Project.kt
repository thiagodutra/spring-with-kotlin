package com.github.thiagodutra.domain.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "Projects")
data class Project (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "field", nullable = false)
    var field: String,

    @Column(name = "abstract")
    var abstract: String,

    @Column(name = "first_keyword")
    var firstKeyword: String,
    @Column(name = "second_keyword")
    var secondKeyword: String,
    @Column(name = "third_keyword")
    var thirdKeyword: String,


    @Column(name = "url")
    var url: String,

    @OneToOne(cascade = [CascadeType.ALL])
    var professors: Professor,

    @OneToMany(mappedBy = "projects")
    @Fetch(FetchMode.JOIN)
    var students: MutableSet<Student> = mutableSetOf<Student>()
        )