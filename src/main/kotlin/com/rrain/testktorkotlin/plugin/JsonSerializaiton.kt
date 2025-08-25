package com.rrain.testktorkotlin.plugin

import com.fasterxml.jackson.databind.*
import com.rrain.util.base.json.configureJson5
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


lateinit var JacksonObjectMapper: ObjectMapper




fun Application.configureJsonSerialization() {
  
  install(ContentNegotiation) {
    // Install Jackson serialization
    jackson {
      JacksonObjectMapper = this.configureJson5()
    }
  }
  
  
  routing {
    get("/json/jackson") {
      call.respond(mapOf("hello" to "world"))
    }
  }
  
  
  
  
  
  
  //import io.ktor.serialization.kotlinx.json.*
  
  // Install kotlinx-serialization
  //json()
  
  
  
  //import com.google.gson.*
  //import io.ktor.serialization.gson.*
  
  // Install gson serialization
  /*gson() {
    
    serializeNulls()
    setPrettyPrinting()
    
    registerTypeAdapter(
      ZonedDateTime::class.java,
      object : JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
        override fun serialize(
          src: ZonedDateTime,
          typeOfSrc: Type,
          context: JsonSerializationContext
        ): JsonElement {
          return JsonPrimitive(src.format(zonedDateTimeFormat))
        }
        override fun deserialize(
          json: JsonElement,
          typeOfT: Type,
          context: JsonDeserializationContext
        ): ZonedDateTime {
          return ZonedDateTime.parse(json.asString, zonedDateTimeFormat)
        }
      }
    )
    
    registerTypeAdapter(
      LocalDate::class.java,
      object : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        override fun serialize(
          src: LocalDate,
          typeOfSrc: Type,
          context: JsonSerializationContext
        ): JsonElement {
          return JsonPrimitive(src.format(localDateFormat))
        }
        override fun deserialize(
          json: JsonElement,
          typeOfT: Type,
          context: JsonDeserializationContext
        ): LocalDate {
          return LocalDate.parse(json.asString, localDateFormat)
        }
      }
    )
    
  }*/
  
}



