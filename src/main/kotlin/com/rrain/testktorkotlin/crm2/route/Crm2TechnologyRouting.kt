package com.rrain.testktorkotlin.crm2.route

import com.rrain.testktorkotlin.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2TechnologyRouting() {
  routing {
    
    
    
    post("/api/technology/list") {
      val dataList = Crm2Store.Technologies
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
    get("/api/technology/list") {
      val dataList = Crm2Store.Technologies
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
  }
}
