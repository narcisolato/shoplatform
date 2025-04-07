package com.shoplatform.service.client

import com.shoplatform.dto.client.ClientDto
import com.shoplatform.repository.ShopRepository
import com.shoplatform.shared.converter.toMenu
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ClientService(
    private val shopRepository: ShopRepository,
) {
    fun getMenu(code: String): ClientDto.Response {
        val shopEntity = shopRepository.findByCode(code)
            ?: throw IllegalArgumentException("Shop with code $code not found")
        return shopEntity.toMenu()
    }
}