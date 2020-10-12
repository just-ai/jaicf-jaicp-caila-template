plugins {
    application
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "0.7.0"
val logback = "1.2.3"
val ktor = "1.3.1"

// Main class to run application on heroku. Either PollingConnectionKt, or WebhookConnectionKt
application {
    mainClassName = "com.justai.jaicf.template.connections.WebhookConnectionKt"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("ch.qos.logback:logback-classic:$logback")

    implementation("com.justai.jaicf:core:$jaicf")
    implementation("com.justai.jaicf:jaicp:$jaicf")
    implementation("com.justai.jaicf:caila:$jaicf")

    implementation("io.ktor:ktor-server-netty:$ktor")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

tasks.create("stage") {
    dependsOn("shadowJar")
}
