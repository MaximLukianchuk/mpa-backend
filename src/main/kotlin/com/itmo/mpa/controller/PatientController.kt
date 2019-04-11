package com.itmo.mpa.controller

import com.itmo.mpa.dto.request.PatientRequest
import com.itmo.mpa.dto.response.PatientResponse
import com.itmo.mpa.service.PatientService
import com.itmo.mpa.service.exception.PatientNotFoundException
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Api("/patients")
@RequestMapping("patients")
class PatientController(private val patientService: PatientService) {

    @ApiOperation("Create patient")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
            @Valid @RequestBody patientRequest: PatientRequest
    ) = patientService.createPatient(patientRequest)

    @ApiOperation("Get all patients")
    @GetMapping
    fun getAll(): List<PatientResponse> = patientService.findAll()

    @ApiOperation("Find patient by id")
    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PatientResponse> {
        val patient = patientService.findPatient(id)
                ?: throw PatientNotFoundException(id)
        return ResponseEntity.ok(patient)
    }
}