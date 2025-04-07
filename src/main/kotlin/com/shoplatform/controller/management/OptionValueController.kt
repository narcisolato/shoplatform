package com.shoplatform.controller.management

import com.shoplatform.dto.management.OptionValueDto
import com.shoplatform.service.management.OptionValueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/management")
class OptionValueController(
    val optionValueService: OptionValueService,
) {
    // option-value
    @PostMapping("/option-value")
    fun create(@RequestBody request: OptionValueDto.Request): ResponseEntity<OptionValueDto.Response> {
        val response = optionValueService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/shop/{code}/option-value")
    fun read(@PathVariable code: String): ResponseEntity<OptionValueDto.Response> {
        val response = optionValueService.read(code)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/option-value")
    fun update(@RequestBody request: OptionValueDto.Request): ResponseEntity<OptionValueDto.Response> {
        val response = optionValueService.update(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/option-value")
    fun upsert(@RequestBody request: OptionValueDto.Request): ResponseEntity<OptionValueDto.Response> {
        val response = optionValueService.upsert(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/option-value")
    fun delete(@RequestBody request: OptionValueDto.Request): ResponseEntity<Unit> {
        val response = optionValueService.delete(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}