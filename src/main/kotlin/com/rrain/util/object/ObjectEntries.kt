package com.rrain.util.`object`

import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties



fun main() {
  
  println("2.objectEntries ${2 .objectEntries()}")
  
  val obj = object {
    val prop = "propVal"
    fun f() = 21
    val getter get() = 444
  }
  println("obj.objectEntries ${obj.objectEntries()}")
  
}





@Suppress("UNCHECKED_CAST")
fun <T : Any>T.objectEntries(): Map<String, Any?> {
  return this::class.memberProperties
    .filter { property ->
      property.visibility == KVisibility.PUBLIC
    }
    .associate { property ->
      property.name to (property as KProperty1<T, *>).get(this)
    }
}

