plugins {
    application
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"

    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "1.2.4"
val logback = "1.2.3"

// Main class to run application on heroku. Either JaicpPollerKt, or JaicpServerKt. Will propagate to .jar main class.
application {
    mainClassName = "com.justai.jaicf.template.connections.JaicpServerKt"
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

    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")
    implementation("com.just-ai.jaicf:telegram:$jaicf")

    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    bootJar {
        archiveFileName.set("app.jar")
        mainClass.set("com.justai.jaicf.spring.ApplicationKt")
    }
}

tasks.create("stage") {
    dependsOn("bootJar")
}

tasks.withType<com.justai.jaicf.plugins.jaicp.build.JaicpBuild> {
    mainClassName.set(application.mainClassName)
}
