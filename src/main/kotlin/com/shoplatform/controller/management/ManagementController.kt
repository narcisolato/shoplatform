package com.shoplatform.controller.management

import com.shoplatform.dto.management.ManagementDto
import com.shoplatform.service.management.ManagementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/management")
class ManagementController(
    val managementService: ManagementService,
) {
    @GetMapping("/shop/{code}/all")
    fun read(@PathVariable code: String): ResponseEntity<ManagementDto.Response> {
        val response = managementService.read(code)
        return ResponseEntity(response, HttpStatus.OK)
    }
}