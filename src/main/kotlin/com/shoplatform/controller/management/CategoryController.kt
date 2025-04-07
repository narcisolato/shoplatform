package com.shoplatform.controller.management

import com.shoplatform.dto.management.CategoryDto
import com.shoplatform.service.management.CategoryService
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
class CategoryController(
    val categoryService: CategoryService,
) {
    // TODO /shop/{code}/category 형태로 변경,  @PathVariable code: String 추가

    // category
    @PostMapping("/category")
    fun create(@RequestBody request: CategoryDto.Request): ResponseEntity<CategoryDto.Response> {
        val response = categoryService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/shop/{code}/category")
    fun read(@PathVariable code: String): ResponseEntity<CategoryDto.Response> {
        val response = categoryService.read(code)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/category")
    fun update(@RequestBody request: CategoryDto.Request): ResponseEntity<CategoryDto.Response> {
        val response = categoryService.update(request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/category")
    fun upsert(@RequestBody request: CategoryDto.Request): ResponseEntity<CategoryDto.Response> {
        val response = categoryService.upsert(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/category")
    fun delete(@RequestBody request: CategoryDto.Request): ResponseEntity<Unit> {
        val response = categoryService.delete(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}