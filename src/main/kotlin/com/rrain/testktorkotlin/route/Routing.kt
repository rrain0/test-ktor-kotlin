package com.rrain.testktorkotlin.route

import com.rrain.testktorkotlin.route.routes.main.configureMainRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun Application.configureRouting() {
  configureMainRoutes()
  
  
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
  }
}
