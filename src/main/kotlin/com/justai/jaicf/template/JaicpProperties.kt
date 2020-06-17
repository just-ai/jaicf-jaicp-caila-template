package com.justai.jaicf.template

import com.justai.jaicf.activator.caila.CailaNLUSettings
import java.io.File
import java.util.*

val jaicfProperties: Properties = Properties().apply {
    load(File("./src/main/conf/jaicp.properties").inputStream())
}

val accessToken: String = jaicfProperties.getProperty("apiToken")
val cailaNLUSettings = CailaNLUSettings(
    accessToken = accessToken,
    confidenceThreshold = 0.2
)
