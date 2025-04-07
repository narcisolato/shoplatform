package com.shoplatform.controller.management

import com.shoplatform.dto.management.ShopDto
import com.shoplatform.service.management.ShopService
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
class ShopController(
    val shopService: ShopService,
) {
    // shop
    @PostMapping("/shop")
    fun create(@RequestBody request: ShopDto.Request): ResponseEntity<ShopDto.Response> {
        val response = shopService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/shop/{code}")
    fun read(@PathVariable code: String): ResponseEntity<ShopDto.Response> {
        val response = shopService.read(code)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/shop")
    fun update(@RequestBody request: ShopDto.Request): ResponseEntity<ShopDto.Response> {
        val response = shopService.update(request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/shop")
    fun upsert(@RequestBody request: ShopDto.Request): ResponseEntity<ShopDto.Response> {
        val response = shopService.upsert(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/shop")
    fun delete(@RequestBody request: ShopDto.Request): ResponseEntity<Unit> {
        val response = shopService.delete(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}