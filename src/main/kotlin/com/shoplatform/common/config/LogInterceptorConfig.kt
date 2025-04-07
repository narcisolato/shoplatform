package com.shoplatform.common.config

import com.shoplatform.common.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

@Configuration
class LogInterceptorConfig: WebMvcConfigurer {

    companion object {
        private fun getIp(request: HttpServletRequest): String {
            return request.getHeader("X-FORWARDED-FOR")
                ?: request.getHeader("Proxy-Client-IP")
                ?: request.getHeader("WL-Proxy-Client-IP")
                ?: request.getHeader("HTTP_CLIENT_IP")
                ?: request.getHeader("HTTP_X_FORWARDED_FOR")
                ?: request.remoteAddr
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LogInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/resources/**")
    }

    class LogInterceptor : HandlerInterceptor {

        companion object {
            private val log = logger()
            const val LOG_ID = "logId"
        }

        override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
            val method = request.method
            val requestPath = request.requestURI
            val logId = UUID.randomUUID().toString().substring(0..8)
            request.setAttribute(LOG_ID, logId)
            val ip = getIp(request)
            log.debug("[Request][$logId][$ip][$method][$requestPath]")
            return true
        }

        override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
            val logId = request.getHeader(LOG_ID)
            log.debug("[Response][$logId]")
            ex?.let {
                log.warn("[Response][$logId] ${it.message}")
                throw it
            }
        }
    }
}