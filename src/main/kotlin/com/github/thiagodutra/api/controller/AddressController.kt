package com.github.thiagodutra.api.controller

import com.github.thiagodutra.api.dto.AddressDTO
import com.github.thiagodutra.api.dto.DefaultResponse
import com.github.thiagodutra.domain.service.IAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import kotlin.NoSuchElementException

@Validated
@RestController
@RequestMapping("/address")
class AddressController(
    @Autowired
    val addressService: IAddressService
) {

    //TODO Improve Generic Response for calls
        //Almost done

    //TODO ADD HttpStatus to API Calls
        //DONE

    @ExceptionHandler(NoSuchElementException::class)
    fun handleException(e: NoSuchElementException): ResponseEntity<DefaultResponse> =
        ResponseEntity(DefaultResponse.Error(
            e.localizedMessage,
            e.stackTraceToString(),
            "No content found with the given id"),
            HttpStatus.NOT_FOUND)


    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintException(e: ConstraintViolationException): ResponseEntity<DefaultResponse> =
        ResponseEntity(DefaultResponse.Error(
            e.localizedMessage,
            e.stackTraceToString(),
            "Some attributes are not compliant with its constraints"),
            HttpStatus.BAD_REQUEST)

    @GetMapping
    @ResponseStatus
    fun retrieveAllAddresses(): ResponseEntity<Collection<AddressDTO>> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.retrieveAllAddresses())
    }

    @GetMapping("/{id}")
    @ResponseStatus
    fun retrieveAddressById(@PathVariable id:Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.retrieveAddressById(id))
    }

    //TODO IMPROVE SEARCH METHOD
    //IDEAS
    /**
     * param keyword
     * object filter
     * remove the default value
     */

    @GetMapping("/search")
    fun retrieveAddressByStreetNameOrZipCode(
        @RequestParam(required = false, defaultValue = "0") streetname: String,
        @RequestParam(required = false, defaultValue = "0") zipcode: String)
    : Collection<AddressDTO> {
        return addressService.retrieveAddressByStreetNameOrZipCode(streetname, zipcode)
    }

    @PostMapping
    fun insertAddress(@Valid @RequestBody addressDTO: Collection<AddressDTO>): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.insertAddress(addressDTO))
    }

    @PutMapping("/{id}")
    fun editAddress(@PathVariable id:Long, @Valid @RequestBody addressDTO: AddressDTO): ResponseEntity<DefaultResponse>  {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(id, addressDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAddressById(id))
    }
}