package com.shoplatform.controller.client

import com.shoplatform.dto.client.ClientDto
import com.shoplatform.service.client.ClientService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(
    val clientService: ClientService
) {

    // TODO 캐시 추가
    @GetMapping("/shop/{code}")
    fun getShopMenu(@PathVariable code: String): ClientDto.Response {
        return clientService.getMenu(code)
    }
}