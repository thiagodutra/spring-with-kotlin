package com.github.thiagodutra.domain.service

import com.github.thiagodutra.api.dto.AddressDTO
import com.github.thiagodutra.api.dto.DefaultResponse
import java.util.*

interface IAddressService {

    fun retrieveAllAddresses(): Collection<AddressDTO>
    fun retrieveAddressById(addressId: Long): DefaultResponse
    fun retrieveAddressByStreetNameOrZipCode(streetName: String, addressZipCode: String): Collection<AddressDTO>
    fun insertAddress(addressDto: Collection<AddressDTO>): DefaultResponse
    fun updateAddress(id: Long, newAddress: AddressDTO): DefaultResponse
    fun deleteAddressById(addressId: Long): DefaultResponse

}