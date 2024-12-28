package com.rrain.testktorkotlin.plugin

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*



fun Application.configureStatusPages() {
  
  install(StatusPages) {
    
    // Configure responses from exceptions
    // Will be configured in specific route handlers
    exception<Throwable> { call, cause ->
      when (cause){
        /*is AuthenticationException -> call.respond(
          HttpStatusCode.Unauthorized,
          object {
            val code = cause.code
            val msg = cause.message
          }
        )*/
        
        else -> {
          call.respondText(
            status = HttpStatusCode.InternalServerError,
            text = "500 Internal Server Error: $cause",
          )
          cause.printStackTrace()
        }
      }
    }
    
    // Configure responses from status codes
    status(HttpStatusCode.NotFound) { call, status ->
      call.respondText(text = "404 Not Found", status = status)
    }
    
    /*
    // Send an html page respectively to status code
    // '#' will be replaced by numeric status code ("error#.html" -> "error404.html")
    // pages can be places in resources/ directory
    install(StatusPages) {
      statusFile(
        HttpStatusCode.Unauthorized, HttpStatusCode.PaymentRequired,
        filePattern = "error#.html"
      )
    }
     */
    
  }
  
  
}