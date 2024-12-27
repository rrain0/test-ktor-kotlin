package com.rrain.testktorkotlin.crm2.route

import com.rrain.testktorkotlin.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2ReferenceRouting() {
  routing {
    
    
    
    post("/api/reference/list") {
      
      val string = call.receiveText()
      println("/api/reference/list text: $string")
      
      Crm2Store.referenceList.also {
        call.respond(
          object {
            val data = it
            val totalSize = it.size
          }
        )
      }
    }
    
    
    
  }
}
