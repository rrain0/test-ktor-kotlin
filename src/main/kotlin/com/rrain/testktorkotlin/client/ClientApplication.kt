package com.rrain.testktorkotlin.client




suspend fun main() {
  val client = createClient()
  
  client.close()
}


