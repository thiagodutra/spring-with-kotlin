package com.github.thiagodutra.domain.repository

import com.github.thiagodutra.domain.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {

    fun findByStreetContainingOrZipcodeIs(street : String, zipCode: String): Collection<Address>
    fun findByZipcodeContaining(zipcode: String): Collection<Address>

}