package com.rrain.plugin

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.core.util.Separators
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.addDeserializer
import com.fasterxml.jackson.module.kotlin.addSerializer
import com.rrain.util.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*




lateinit var JacksonObjectMapper: ObjectMapper




fun Application.configureJsonSerialization() {
  install(ContentNegotiation) {
    // install Jackson serialization
    jackson {
      JacksonObjectMapper = this
      
      configure(SerializationFeature.INDENT_OUTPUT, true)
      setDefaultPrettyPrinter(
        DefaultPrettyPrinter(
        // no space before colon and have space after colon
        Separators.createDefaultInstance().withObjectFieldValueSpacing(Separators.Spacing.AFTER)
      )
      )
      /*setDefaultPrettyPrinter({
        class MyPrettyPrinter : DefaultPrettyPrinter() {
          override fun createInstance(): DefaultPrettyPrinter = MyPrettyPrinter()
          override fun writeObjectFieldValueSeparator(g: JsonGenerator) = g.writeRaw(": ")
        }
        MyPrettyPrinter()
      }())*/
      /*setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
        indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
        indentObjectsWith(DefaultIndenter("  ", "\n"))
      })*/
      
      configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
      
      // support java.time.* types
      registerModule(JavaTimeModule())
      
      registerModule(SimpleModule()
        // ZonedDateTime
        .addSerializer(
          ZonedDateTime::class, object : StdSerializer<ZonedDateTime>(ZonedDateTime::class.java) {
            override fun serialize(value: ZonedDateTime, gen: JsonGenerator, provider: SerializerProvider) {
              return gen.writeString(value.format(zonedDateTimeFormatter))
            }
          }
        )
        .addDeserializer(
          ZonedDateTime::class, object : StdDeserializer<ZonedDateTime>(ZonedDateTime::class.java) {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ZonedDateTime {
              return p.valueAsString.toZonedDateTime()
            }
          }
        )
        
        // LocalDate
        .addSerializer(
          LocalDate::class, object : StdSerializer<LocalDate>(LocalDate::class.java) {
            override fun serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
              return gen.writeString(value.format(localDateFormat))
            }
          }
        )
        .addDeserializer(
          LocalDate::class, object : StdDeserializer<LocalDate>(LocalDate::class.java) {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate {
              return p.valueAsString.toLocalDate()
            }
          }
        )
        
        // java.util.UUID
        .addSerializer(
          UUID::class, object : StdSerializer<UUID>(UUID::class.java) {
            override fun serialize(value: UUID, gen: JsonGenerator, provider: SerializerProvider) {
              return gen.writeString(value.toString())
            }
          }
        )
        .addDeserializer(
          UUID::class, object : StdDeserializer<UUID>(UUID::class.java) {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext): UUID {
              return p.valueAsString.toUuid()
            }
          }
        )
      )
      
      // support Kotlin
      registerModule(
        KotlinModule.Builder()
        .configure(KotlinFeature.StrictNullChecks, true)
        .build()
      )
      
    }
  }
  routing {
    get("/json/jackson") {
      call.respond(mapOf("hello" to "world"))
    }
  }
}
