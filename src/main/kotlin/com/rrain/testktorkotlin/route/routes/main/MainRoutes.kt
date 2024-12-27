package com.rrain.testktorkotlin.route.routes.main


import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*




fun Application.configureMainRoutes() {
  
  routing {
    
    get("/ktor/hello") {
      call.respondText("Hello Ktor!")
    }
    
  }
  
}