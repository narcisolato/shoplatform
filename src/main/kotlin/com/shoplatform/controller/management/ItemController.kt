package com.shoplatform.controller.management

import com.shoplatform.dto.management.ItemDto
import com.shoplatform.service.management.ItemService
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
class ItemController(
    val itemService: ItemService,
) {

    // item
    @PostMapping("/item")
    fun create(@RequestBody request: ItemDto.Request): ResponseEntity<ItemDto.Response> {
        val response = itemService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/shop/{code}/item")
    fun read(@PathVariable code: String): ResponseEntity<ItemDto.Response> {
        val response = itemService.read(code)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PatchMapping("/item")
    fun update(@RequestBody request: ItemDto.Request): ResponseEntity<ItemDto.Response> {
        val response = itemService.update(request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/item")
    fun upsert(@RequestBody request: ItemDto.Request): ResponseEntity<ItemDto.Response> {
        val response = itemService.upsert(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/item")
    fun delete(@RequestBody request: ItemDto.Request): ResponseEntity<Unit> {
        val response = itemService.delete(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}