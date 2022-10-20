package com.justai.jaicf.template.config

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.scenario.MainScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun botApi(mainScenario: MainScenario) = BotEngine(

        scenario = mainScenario,
        conversationLoggers = arrayOf(
            JaicpConversationLogger(accessToken),
            Slf4jConversationLogger()
        ),
        activators = arrayOf(
            CailaIntentActivator.Factory(
                CailaNLUSettings(
                    accessToken = accessToken
                )
            ),
            RegexActivator
        )
    )

    @Bean
    fun jaicpWebhookConnector(botApi: BotApi) = JaicpWebhookConnector(
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