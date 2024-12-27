package com.rrain.testktorkotlin.crm2.route

import com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.EmptyGet
import com.rrain.testktorkotlin.crm2.`dto-out`.Crm2DtoOut.toDto
import com.rrain.testktorkotlin.crm2.store.Crm2Store
import com.rrain.testktorkotlin.crm2.store.Crm2Store.Companies
import com.rrain.testktorkotlin.crm2.store.Crm2Store.CompanyToSegments
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*






fun Application.configureCrm2CompanyRouting() {
  routing {
    
    
    
    get("/api/company/{id}") {
      
      println("/api/company/{id} text ${call.receiveText()}")
      
      val id = call.parameters["id"]!!
      
      val dto = Companies.filter { it.id == id }.toDto(EmptyGet)[0]
      
      call.respond( object {
        val data = dto
      } )
    }
    
    
    post("/api/company/list") {
      
      //println("/api/company/list text ${call.receiveText()}")
      
      val getData = call.receive<com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Get>()
      println("reqData $getData")
      
      val dtoList = Companies.toDto(getData)
      
      call.respond( object {
          val data = dtoList
          val totalSize = dtoList.size
      } )
    }
    
    
    
    put("/api/company/list") {
      /*val string = call.receiveText()
      println("PUT /api/company/list text: $string")*/
      
      val data = call.receive<Map<String, Any?>>()
      println("data $data")
      
      Companies.replaceAll { company ->
        if (company.id == data["id"]) {
          data.forEach { (prop, value) ->
            if (prop == "segments") {
              if (value is List<*>) {
                println("value is list")
                CompanyToSegments = CompanyToSegments
                  .filter { it.companyId != company.id }
                  .toMutableList()
                if (value.isNotEmpty() && value.first() is String) {
                  CompanyToSegments.addAll(
                    value.filterIsInstance<String>().map { v ->
                      Crm2Store.CompanyToSegment(mutableMapOf(
                        "companyId" to company.id,
                        "segmentId" to v,
                      ))
                    }
                  )
                }
              }
            }
            else if (prop in company.map && prop != "id") {
              company.map[prop] = value
            }
          }
        }
        company
      }
      
      
      call.respond(HttpStatusCode.OK)
    }
    
    
    
    
  }
}
