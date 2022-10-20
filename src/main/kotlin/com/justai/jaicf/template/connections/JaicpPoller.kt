package com.justai.jaicf.template.connections

import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.templateBot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class JaicpPoller(
    private val botApi: BotApi
) : ApplicationRunner, CoroutineScope {
    override val coroutineContext = Dispatchers.Default

    override fun run(args: ApplicationArguments?) {
        launch {
            JaicpPollingConnector(
                botApi = botApi,
                accessToken = accessToken,
                channels = listOf(
                    ChatApiChannel,
                    ChatWidgetChannel,
                    TelephonyChannel,
                    TelegramChannel
                )
            )
        }
    }
}

fun main() {
    JaicpPollingConnector(
        botApi = templateBot,
        accessToken = accessToken,
        channels = listOf(
            ChatApiChannel,
            ChatWidgetChannel,
            TelephonyChannel,
            TelegramChannel
        )
    ).runBlocking()
}