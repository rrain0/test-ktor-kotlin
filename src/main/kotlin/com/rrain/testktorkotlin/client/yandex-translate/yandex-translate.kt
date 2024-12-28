package com.rrain.testktorkotlin.client.`yandex-translate`

import com.rrain.testktorkotlin.client.createClient
import com.rrain.testktorkotlin.service.json.createJacksonJson5Mapper
import com.rrain.testktorkotlin.util.cast
import com.rrain.testktorkotlin.util.getResourcePath
import com.rrain.testktorkotlin.util.println
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText


suspend fun main() {
  val client = createClient()
  translate(client)
  client.close()
}


data class TranslateData(
  val folderId: String = "b1g8v7be24fi2so67n5m",
  val texts: List<String> = listOf("Hello World!", "Hello \nnew line!"),
  val sourceLanguageCode: String = "en",
  val targetLanguageCode: String = "ru",
)

suspend fun HttpClient.testYandexTranslateRequest() {
  
  val response = post("https://translate.api.cloud.yandex.net/translate/v2/translate") {
    headers {
      bearerAuth("t1.9euelZqQzZnKkpuUjZOVyZPPzoqax-3rnpWaj4_Im8aazsaUlo3LisrOxorl8_dNNSVJ-e84Y3Uq_????????????????????????????????????????")
      contentType(ContentType.Application.Json)
    }
    setBody(TranslateData())
  }
  
  val responseStr = response.bodyAsText()
  println("responseStr: $responseStr")
  /*
  Ответ:
  {
      "translations": [
          {
              "text": "Привет, мир!"
          },
          {
              "text": "Привет\n, новая линия!"
          }
      ]
  }
   */
  
}


suspend fun HttpClient.yandexTranslateRequest(data: TranslateData): List<String> {
  
  val response = post("https://translate.api.cloud.yandex.net/translate/v2/translate") {
    headers {
      bearerAuth("t1.9euelZqQzZnKkpuUjZOVyZPPzoqax-3rnpWaj4_Im8aazsaUlo3LisrOxorl8_dNNSVJ-e84Y3Uq_?????????????????????????????????????????")
      contentType(ContentType.Application.Json)
    }
    setBody(data)
  }
  
  val responseBody = response.body<Map<String, Any?>>()
  println("responseStr: $responseBody")
  
  return responseBody["translations"]
    .cast<List<Map<String, String>>>().map { it["text"] ?: "" }
}


fun flattenMapToList(map: Map<String, Any?>, list: MutableList<String>) {
  map.forEach { (name, v) ->
    if (v is String) list += v
    else if (v is Map<*, *>) flattenMapToList(v.cast(), list)
  }
}
fun unflattenListToMap(iterator: Iterator<String>, structureMap: MutableMap<String, Any?>, map: MutableMap<String, Any?>) {
  structureMap.forEach { (name, v) ->
    if (v is String) map[name] = iterator.next()
    else if (v is Map<*, *>) unflattenListToMap(
      iterator,
      v.cast(),
      map.getOrPut(name) { mutableMapOf<String, Any?>() }.cast(),
    )
  }
}


suspend fun translate(client: HttpClient) {
  val sourceText = getResourcePath("/yandex-translate/checkers.en.translations.json5").readText()
  
  println("sourceText:", sourceText)
  
  val json5Mapper = createJacksonJson5Mapper()
  
  val source = json5Mapper.readValue(sourceText, Map::class.java)
  
  println("source:", source)
  
  val sourceEn = source.cast<Map<String, Any>>()["en"]
  
  val sourceEnList: MutableList<String> = mutableListOf()
  flattenMapToList(sourceEn.cast(), sourceEnList)
  println("sourceEnList:", sourceEnList)
  
  val translateData = TranslateData(
    texts = sourceEnList,
    sourceLanguageCode = "en",
    // Вьетнамский "vi", Корейский "ko", Японский "ja", Польский "pl"
    targetLanguageCode = "ja",
  )
  
  val translationList = client.yandexTranslateRequest(translateData)
  
  val translations: MutableMap<String, Any?> = mutableMapOf()
  
  unflattenListToMap(translationList.iterator(), sourceEn.cast(), translations)
  println("translationsMap:", translations)
  
  val translationsJson5 = json5Mapper.writeValueAsString(translations)
  println("translationsJson5:", translationsJson5)
  
  Path("checkers.${translateData.targetLanguageCode}.translations.json5").writeText(translationsJson5)
}



