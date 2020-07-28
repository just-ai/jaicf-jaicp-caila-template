package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.http.httpBotRouting
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.templateBot
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, System.getenv("PORT")?.toInt() ?: 8080) {
        routing {
            httpBotRouting(
                "/" to JaicpWebhookConnector(
                    templateBot,
                    accessToken,
                    channels = listOf(
                        ChatApiChannel,
                        ChatWidgetChannel,
                        TelephonyChannel
                    )
                )
            )
        }
    }.start(wait = true)
}