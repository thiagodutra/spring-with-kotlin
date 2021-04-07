package com.github.thiagodutra.domain.service.address

import com.github.thiagodutra.api.dto.AddressDTO
import com.github.thiagodutra.domain.model.Address
import com.github.thiagodutra.domain.repository.AddressRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
internal class AddressServiceTest(
    @MockK
    val addressRepository: AddressRepository
) {

    var mockAddressService = AddressService(addressRepository)

    private var addressList = mutableListOf(
        Address(1, "benedito mota", "1169", "58401691", "campina grade", "PB", "Brazil"),
        Address(2, "jose bernardino", "97", "58401692", "campina grade", "PB", "Brazil"),
        Address(3, "Alfredo pimentel", "169", "58401693", "campina grade", "PB", "Brazil"),
        Address(4, "Agamenon magalhaes", "69", "58401694", "campina grade", "PB", "Brazil"),
        Address(5, "25 de março", "961", "58401695", "campina grade", "PB", "Brazil"),
        Address(6, "25 de março", "961", "58401691", "campina grade", "PB", "Brazil"),
        Address(7, "25 de março", "961", "58401692", "campina grade", "PB", "Brazil"),
        Address(8, "25 de março", "961", "58401693", "campina grade", "PB", "Brazil"),
        Address(9, "25 de março", "961", "58401695", "campina grade", "PB", "Brazil"),
        Address(10, "25 de março", "961", "58401695", "campina grade", "PB", "Brazil"),
        Address(11, "25 de março", "961", "58401699", "campina grade", "PB", "Brazil")
    )

    private val addressListDto = addressList.map { AddressDTO(it.id, it.street, it.number, it.zipcode, it.city, it.state, it.country)  }

    @Test
    fun `should retrieve and address by id`() {
        mockkObject(addressRepository)
        val listIndexId = Random.nextLong(0,5)
        val notInListId = 20L
        every { addressRepository.findById(listIndexId) } returns Optional.of(addressList[listIndexId.toInt()])
        every { addressRepository.findById(notInListId) } returns Optional.empty()
        Assertions.assertEquals(listOf(addressListDto[listIndexId.toInt()]), mockAddressService.retrieveAddressById(listIndexId))
        Assertions.assertTrue(addressRepository.findById(notInListId).isEmpty)
    }

    @Test
    fun `should retrieveAllAddress`() {
        mockkObject(addressRepository)
        every { addressRepository.findAll() } returns addressList
        Assertions.assertTrue(mockAddressService.retrieveAllAddresses().isNotEmpty())
    }

    @Test
    fun `should remove an Address`() {
        val originalSize = addressList.size
        mockkObject(addressRepository)
        every { addressRepository.deleteById(any()) } .also { addressList.removeAt(Random.nextInt(0, 5)) }
        Assertions.assertTrue(originalSize > addressList.size)
    }

    @Test
    fun `should search an address by zipcode`() {
        mockkObject(addressRepository)
        val listOfZipCodes = addressList.map {
            it.zipcode
        }
        val randomIndex = Random.nextInt(0,12)
        every { addressRepository.findByZipcodeLike(listOfZipCodes[randomIndex]) } returns addressList.filter { it.zipcode == (listOfZipCodes[randomIndex]) }
        every { addressRepository.findByZipcodeLike("NotInList") } returns addressList.filter { it.zipcode == (listOfZipCodes[randomIndex]) }
        Assertions.assertTrue(addressRepository.findByZipcodeLike(listOfZipCodes[randomIndex]).isNotEmpty())
        Assertions.assertTrue(addressRepository.findByZipcodeLike("NotInList").isNotEmpty())
    }
}
