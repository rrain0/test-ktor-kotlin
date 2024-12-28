package com.rrain.testktorkotlin.plugin

import com.fasterxml.jackson.databind.*
import com.rrain.util.json.configureJson5
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


lateinit var JacksonObjectMapper: ObjectMapper




fun Application.configureJsonSerialization() {
  install(ContentNegotiation) {
    // install Jackson serialization
    jackson {
      JacksonObjectMapper = this.configureJson5()
    }
  }
  routing {
    get("/json/jackson") {
      call.respond(mapOf("hello" to "world"))
    }
  }
}
