package com.justai.jaicf.template

import com.justai.jaicf.activator.caila.CailaNLUSettings
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*

@SpringBootConfiguration
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.justai.jaicf.template.config")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

val accessToken: String = System.getenv("JAICP_API_TOKEN") ?: Properties().run {
    load(CailaNLUSettings::class.java.getResourceAsStream("/jaicp.properties"))
    getProperty("apiToken")
}