package com.shoplatform.controller.management

import com.shoplatform.dto.management.OptionDto
import com.shoplatform.service.management.OptionService
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
class OptionController(
    val optionService: OptionService,
) {
    // option
    @PostMapping("/option")
    fun create(@RequestBody request: OptionDto.Request): ResponseEntity<OptionDto.Response> {
        val response = optionService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/shop/{code}/option")
    fun read(@PathVariable code: String): ResponseEntity<OptionDto.Response> {
        val response = optionService.read(code)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/option")
    fun update(@RequestBody request: OptionDto.Request): ResponseEntity<OptionDto.Response> {
        val response = optionService.update(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/option")
    fun upsert(@RequestBody request: OptionDto.Request): ResponseEntity<OptionDto.Response> {
        val response = optionService.upsert(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/option")
    fun delete(@RequestBody request: OptionDto.Request): ResponseEntity<Unit> {
        val response = optionService.delete(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}