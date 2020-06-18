package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.context.manager.InMemoryBotContextManager
import com.justai.jaicf.context.manager.mongo.MongoBotContextManager
import com.justai.jaicf.template.scenario.MainScenario
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import java.io.File
import java.util.*

private val contextManager = System.getenv("MONGODB_URI")?.let { url ->
    val uri = MongoClientURI(url)
    val client = MongoClient(uri)
    MongoBotContextManager(client.getDatabase(uri.database!!).getCollection("contexts"))
} ?: InMemoryBotContextManager

val accessToken: String = System.getenv("JAICP_ACCESS_TOKEN") ?: Properties().apply {
    load(File("./src/main/conf/jaicp.properties").inputStream())
}.getProperty("apiToken")

private val cailaNLUSettings = CailaNLUSettings(
    accessToken = accessToken,
    confidenceThreshold = 0.2
)

val templateBot = BotEngine(
    model = MainScenario.model,
    contextManager = contextManager,
    activators = arrayOf(
        CailaIntentActivator.Factory(cailaNLUSettings),
        RegexActivator,
        CatchAllActivator
    )
)

