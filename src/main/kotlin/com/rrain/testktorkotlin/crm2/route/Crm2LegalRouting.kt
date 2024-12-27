package com.rrain.testktorkotlin.crm2.route

import com.rrain.testktorkotlin.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*




fun Application.configureCrm2LegalRouting() {
  routing {
    
    
    
    post("/api/legal/list") {
      val dataList = Crm2Store.Legals
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
    get("/api/legal/list") {
      val dataList = Crm2Store.Legals
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
  }
}
