package com.rrain.client

import com.rrain.client.plugins.configureJacksonPrettier
import com.rrain.client.plugins.configureJacksonTypes
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*


fun createClient() = HttpClient(CIO) {
  // enable response validation
  expectSuccess = true
  
  // Configure an engine
  engine {
  
  }
  
  // configure logging
  install(Logging) {
    logger = Logger.DEFAULT
    level = LogLevel.HEADERS
    filter { request ->
      request.url.host.contains("ktor.io")
    }
    sanitizeHeader { header -> header == HttpHeaders.Authorization }
  }
  
  // install Content Negotiation plugin
  install(ContentNegotiation) {
    jackson {
      configureJacksonPrettier()
      configureJacksonTypes()
    }
  }
  
}