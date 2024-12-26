package com.rrain.crm2.route

import com.rrain.crm2.store.Crm2Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2SegmentRouting() {
  routing {
    
    
    
    post("/api/segment/list") {
      
      /*val string = call.receiveText()
      println("/api/brand/list text: $string")*/
      
      val dataList = Crm2Store.Segments
      
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
    
    get("/api/segment/list") {
      val dataList = Crm2Store.Segments
      call.respond(object {
        val data = dataList
        val totalSize = dataList.size
      } )
    }
    
    
  }
}
