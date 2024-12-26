
plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor)
  alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.rrain"
version = "0.0.1"

application {
  mainClass.set("io.ktor.server.jetty.jakarta.EngineMain")
  
  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
  mavenCentral()
}

dependencies {
  // ktor client
  implementation("io.ktor:ktor-client-core:${libs.versions.ktor.version}")
  // ktor client engine
  implementation("io.ktor:ktor-client-cio:${libs.versions.ktor.version}")
  // ktor client logging
  implementation("io.ktor:ktor-client-logging:${libs.versions.ktor.version}")
  // ktor client content negotiation (response body serialization, request body deserialization, ...)
  implementation("io.ktor:ktor-client-content-negotiation:${libs.versions.ktor.version}")
  
  // ktor server
  implementation(libs.ktor.server.core)
  // ktor server engine
  implementation(libs.ktor.server.jetty.jakarta)
  
  // SLF4J - Simple Logging Facade for Java
  implementation(libs.slf4j.api)
  implementation(libs.jcl.over.slf4j)
  implementation(libs.logback.core)
  implementation(libs.logback.classic)
  
  // ktor client content negotiation (response body serialization, request body deserialization, ...)
  implementation(libs.ktor.server.content.negotiation)
  // ktor serialization via Jackson
  implementation(libs.ktor.serialization.jackson)
  // Kotlin Jackson Support
  // https://github.com/FasterXML/jackson-module-kotlin
  implementation(libs.jackson.module.kotlin)
  // Java Time Jackson Support
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
  implementation(libs.jackson.java.time)
  
  // Ktor plugins
  // use proxy server forwarded & x-forwarded headers
  implementation(libs.ktor.forwarded.headers)
  implementation("io.ktor:ktor-server-default-headers-jvm:${libs.versions.ktor.version}")
  implementation("io.ktor:ktor-server-cors-jvm:${libs.versions.ktor.version}")
  
}
