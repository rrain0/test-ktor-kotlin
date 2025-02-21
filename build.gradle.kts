
plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor)
  alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.rrain.testktorkotlin"
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
  // Kotlin
  // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
  implementation(libs.kotlin.coroutines.core)
  
  // Ktor server
  implementation(libs.ktor.server.core)
  // ktor server engine
  implementation(libs.ktor.server.jetty.jakarta)
  
  // SLF4J - Simple Logging Facade for Java
  implementation(libs.slf4j.api)
  implementation(libs.jcl.over.slf4j)
  implementation(libs.logback.core)
  implementation(libs.logback.classic)
  
  // Ktor client content negotiation (response body serialization, request body deserialization, ...)
  implementation(libs.ktor.server.content.negotiation)
  // Ktor serialization via Jackson
  implementation(libs.ktor.serialization.jackson)
  // Kotlin Jackson Support
  // https://github.com/FasterXML/jackson-module-kotlin
  implementation(libs.jackson.module.kotlin)
  // Java Time Jackson Support
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
  implementation(libs.jackson.java.time)
  
  // Other ktor plugins
  // Use proxy server forwarded & x-forwarded headers
  implementation(libs.ktor.forwarded.headers)
  implementation(libs.ktor.caching.headers)
  implementation(libs.ktor.auto.head.response)
  implementation(libs.ktor.status.pages)
  implementation(libs.ktor.call.id)
  implementation(libs.ktor.call.logging)
  implementation("io.ktor:ktor-server-cors:${libs.versions.ktor.version}")
  implementation("io.ktor:ktor-server-default-headers:${libs.versions.ktor.version}")
  implementation("io.ktor:ktor-server-resources:${libs.versions.ktor.version}")
  implementation("io.ktor:ktor-server-partial-content:${libs.versions.ktor.version}")
  
  // Ktor client
  implementation("io.ktor:ktor-client-core:${libs.versions.ktor.version}")
  // Ktor client engine
  implementation("io.ktor:ktor-client-cio:${libs.versions.ktor.version}")
  // Ktor client logging
  implementation("io.ktor:ktor-client-logging:${libs.versions.ktor.version}")
  // Ktor client content negotiation (response body serialization, request body deserialization, ...)
  implementation("io.ktor:ktor-client-content-negotiation:${libs.versions.ktor.version}")
  
  // Testing
  testImplementation(libs.ktor.server.test.host)
  testImplementation(libs.kotlin.test.junit)
}
