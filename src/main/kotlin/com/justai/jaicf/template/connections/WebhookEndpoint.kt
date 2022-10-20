package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.http.asHttpBotRequest
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookEndpoint(private val jaicpWebhookConnector: JaicpWebhookConnector) {

    @PostMapping("/webhook")
    fun processRequest(@RequestBody request: String) =
        jaicpWebhookConnector.process(request.asHttpBotRequest()).let { response ->
            ResponseEntity
                .status(response.statusCode)
                .headers { it.setAll(response.headers) }
                .body(response.output.toString())
        }
}