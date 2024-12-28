package com.rrain.testktorkotlin.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureHttpDefaultHeaders() {
  
  /*
    The DefaultHeaders plugin adds the standard Server and Date headers into each response.
    Moreover, you can provide additional default headers and override the Server header.
    
    Note that the Date header is cached due to performance reasons and cannot be overridden by using DefaultHeaders.
   */
  
  install(DefaultHeaders) {
    // Some custom header.
    // Will send this header with each response.
    /*
      Totally it adds:
      X-Engine: Ktor
      Date: Sat, 28 Dec 2024 05:24:16 GMT
      Server: Ktor/3.0.2
     */
    header("X-Engine", "Ktor")
  }
  
  
}
