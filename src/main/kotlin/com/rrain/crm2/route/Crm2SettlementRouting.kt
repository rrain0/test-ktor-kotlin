package com.rrain.crm2.route

import com.rrain.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2SettlementRouting() {
  routing {
    
    
    
    post("/api/settlement/list") {
      val dataList = Crm2Store.Settlements
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
    get("/api/settlement/list") {
      val dataList = Crm2Store.Settlements
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
  }
}
