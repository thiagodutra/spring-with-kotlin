package com.github.thiagodutra.domain.service.address

import com.github.thiagodutra.api.dto.AddressDTO
import com.github.thiagodutra.api.dto.DefaultResponse
import com.github.thiagodutra.domain.model.Address
import com.github.thiagodutra.domain.repository.AddressRepository
import com.github.thiagodutra.domain.service.IAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(@Autowired val addressRepository: AddressRepository): IAddressService {

    //TODO Add Validators and Mappers

    override fun retrieveAllAddresses(): Collection<AddressDTO> {
        return addressRepository.findAll().map {
            AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)
        }
    }

    //TODO CHANGE RETURN TO DefaultResponse
    override fun retrieveAddressById(addressId: Long): DefaultResponse {
        val address = addressRepository.findById(addressId).get().let {
            AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)
        }
        return DefaultResponse.Success("retrieveAddressById", "Success", Optional.of(address))
    }

    override fun retrieveAddressByStreetNameOrZipCode(streetName: String, addressZipCode: String): Collection<AddressDTO> {
        return addressRepository.findByStreetContainingOrZipcodeIs(streetName, addressZipCode).map {
            AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)
        }
    }

    override fun insertAddress(addressDto: Collection<AddressDTO>): DefaultResponse {
        return try {
            val newAddresses = addressRepository
                .saveAll(
                    addressDto.map {
                        Address(null, it.street, it.number, it.zipcode, it.street, it.state, it.country )
                    }
                ).map {
                    AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)
                }
            DefaultResponse.Success("insertAddress", "Success", Optional.of(newAddresses))
        } catch (e: IllegalArgumentException ) {
            DefaultResponse.Error("insertAddress", e.stackTraceToString(), e.localizedMessage)
        }
    }

    //TODO REFACTOR THIS TO USE PATH PARAM INSTEAD OF ENTIRE OBJECT -> DONE
    override fun updateAddress(id: Long, address: AddressDTO): DefaultResponse {
        return try {
            if (checkIfAddressExistsById(id)) {
                return DefaultResponse.Success("insertAddress", "Success", mapAndUpdateAddress(id, address))
            }
            DefaultResponse.Error(
                "insertAddress",
                "There isn't an address with the given id = $id. Object $address",
                "Something wrong occurred during update address operation"
            )
        } catch (e: IllegalArgumentException) {
            DefaultResponse.Error(
                "insertAddress",
                e.stackTraceToString(),
                "Something wrong occurred during update address operation"
            )
        }
    }

    override fun deleteAddressById(addressId: Long): DefaultResponse {
        return try {
            if (checkIfAddressExistsById(addressId)) {
                val deletedAddress = addressRepository.findById(addressId)
                addressRepository.deleteById(addressId)
                return DefaultResponse.Success("deleteAddressById", "Success", mapAddressToDto(deletedAddress))
            }
            DefaultResponse.Error("deleteAddressById", "Address not found", "Couldn't find an address with the given id: $addressId")
        } catch (e: Exception) {
            DefaultResponse.Error("deleteAddressById", e.stackTraceToString(), "Couldn't find an address with the given id: $addressId")
        }
    }

    private fun mapAddressToDto(address: Optional<Address>): Optional<AddressDTO> {
        return address.map { AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country) }
    }

    private fun checkIfAddressExistsById(id: Long): Boolean {
        return addressRepository.existsById(id)
    }

    private fun mapAndUpdateAddress(id: Long, address: AddressDTO): Optional<AddressDTO> {
            return Optional.of(
                addressRepository.save(
                    Address(address.id, address.street, address.number, address.zipcode, address.city, address.state, address.country)
                )
            ).map {
                AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)
            }
    }
}