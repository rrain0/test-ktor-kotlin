package com.rrain.testktorkotlin.route

import com.rrain.testktorkotlin.route.resources.configureResourceRouting
import com.rrain.testktorkotlin.route.routes.main.configureMainRoutes
import com.rrain.testktorkotlin.route.routes.test.configureJsonSerializationTestRoutes
import com.rrain.testktorkotlin.route.routes.test.configureStaticTestRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*




fun Application.configureRouting() {
  
  configureMainRoutes()
  configureJsonSerializationTestRoutes()
  configureStaticTestRoutes()
  
  configureResourceRouting()
  
  
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
    
    
    // Fallback route handler if no match
    // The {...} is a tailcard and matches any request that hasn't been matched yet.
    get("{...}"){
      call.respond("no matching route")
    }
  }
  
  
}
