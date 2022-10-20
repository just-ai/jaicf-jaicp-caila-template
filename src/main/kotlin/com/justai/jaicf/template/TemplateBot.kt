package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.template.scenario.MainScenario

private val cailaNLUSettings = CailaNLUSettings(
    accessToken = accessToken
)

val templateBot = BotEngine(
    scenario = MainScenario(),
    conversationLoggers = arrayOf(
        JaicpConversationLogger(accessToken),
        Slf4jConversationLogger()
    ),
    activators = arrayOf(
        CailaIntentActivator.Factory(cailaNLUSettings),
        RegexActivator
    )
)
