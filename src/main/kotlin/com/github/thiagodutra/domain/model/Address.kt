package com.github.thiagodutra.domain.model

import javax.persistence.*

@Entity(name = "Address")
data class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "street", length = 80, nullable = false)
        var street: String,
        @Column(name = "number", length = 10, nullable = false)
        var number: String,
        @Column(name = "zipcode", length = 8, nullable = false)
        var zipcode: String,
        @Column(name = "city", length = 50, nullable = false)
        var city: String,
        @Column(name = "state", length = 20, nullable = false)
        var state: String,
        @Column(name = "country", length = 25, nullable = false)
        var country: String
)

