package com.rrain.util.`object`

import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility



fun main() {
  
  val obj = object {
    val prop = "propVal"
    fun f() = 21
    val getter get() = 444
  }
  println("""obj.objectGet("prop") ${obj.objectGet("prop")}""")
  println("""obj.objectGet("nothing") ${obj.objectGet("nothing")}""")
  
}


@Suppress("UNCHECKED_CAST")
fun Any.objectGet(propName: String): Any? {
  val prop = this::class.members
    .first {
      it.name == propName && it.visibility == KVisibility.PUBLIC
    } as KProperty1<Any, *>
  return prop.get(this)
}


