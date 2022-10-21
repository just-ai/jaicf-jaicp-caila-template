package com.justai.jaicf.template

import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    System.setProperty("spring.config.location",
        "classpath:/application.properties,classpath:/dev.application.properties")
    runApplication<Application>(*args)
}