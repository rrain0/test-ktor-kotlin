package com.rrain.testktorkotlin.client.plugins

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.util.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.*
import com.rrain.testktorkotlin.util.*
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*


fun ObjectMapper.configureJacksonPrettier() {
  configure(SerializationFeature.INDENT_OUTPUT, true)
  
  setDefaultPrettyPrinter(DefaultPrettyPrinter(
    // no space before colon and have space after colon
    Separators.createDefaultInstance().withObjectFieldValueSpacing(Separators.Spacing.AFTER)
  ))
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
}


fun ObjectMapper.configureJacksonTypes() {
  
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
    
    // ObjectId for MongoDB
    /*.addSerializer(
      ObjectId::class, object : StdSerializer<ObjectId>(ObjectId::class.java) {
        override fun serialize(value: ObjectId, gen: JsonGenerator, provider: SerializerProvider) {
          return gen.writeString(value.toHexString())
        }
      }
    )
    .addDeserializer(
      ObjectId::class, object : StdDeserializer<ObjectId>(ObjectId::class.java) {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ObjectId {
          return p.valueAsString.let(::ObjectId)
        }
      }
    )*/
  
  )
  
  // support Kotlin
  registerModule(
    KotlinModule.Builder()
    .configure(KotlinFeature.StrictNullChecks, true)
    .build()
  )
  
}

