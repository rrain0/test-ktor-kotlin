package com.rrain.testktorkotlin.util

import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.io.path.toPath


// path is relative to "resources" folder, but starts with "/"
// for example: "/yandex-translate/example.en.translations.json5"
fun getResourceUrl(path: String): URL = object{}.javaClass.getResource(path) ?: throw FileNotFoundException()
fun getResourcePath(path: String): Path = getResourceUrl(path).toURI().toPath()

private fun testGetResource() {
  val content = getResourceUrl("/yandex-translate/example.en.translations.json5")
    .toURI()
    .also {
      // URI: file:/D:/PROG/Kotlin/%5bprojects%5d/test-ktor-kotlin/build/resources/main/yandex-translate/example.en.translations.json5
      println("URI: $it")
    }
    .toPath()
    .readText()
  
  println(content)
}



fun main() {
  testGetResource()
}


