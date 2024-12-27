package com.rrain.testktorkotlin.crm2.route

import com.rrain.testktorkotlin.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2CuratorRouting() {
  routing {
    
    
    
    post("/api/curator/list") {
      val dataList = Crm2Store.Curators
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
    get("/api/curator/list") {
      val dataList = Crm2Store.Curators
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
  }
}
