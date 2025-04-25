package com.shoplatform.common.config

import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class P6SpyConfig: MessageFormattingStrategy {

    @PostConstruct
    fun setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().logMessageFormat = this::class.java.name
    }

    override fun formatMessage(
        connectionId: Int,
        now: String?,
        elapsed: Long,
        category: String?,
        prepared: String?,
        sql: String?,
        url: String?
    ): String {
        return "$category | $elapsed ms | $sql"
    }
}