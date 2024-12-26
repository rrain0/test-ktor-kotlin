package com.rrain

import com.rrain.crm2.route.*
import com.rrain.plugin.*
import com.rrain.route.configureRouting
import io.ktor.server.application.*



fun main(args: Array<String>): Unit = io.ktor.server.jetty.jakarta.EngineMain.main(args)

fun Application.module() {
  configureJsonSerialization()
  configureHTTP()
  configureRouting()
  
  
  configureCrm2ReferenceRouting()
  
  configureCrm2LegalRouting()
  configureCrm2BrandRouting()
  configureCrm2SettlementRouting()
  configureCrm2CuratorRouting()
  configureCrm2TechnologyRouting()
  configureCrm2SegmentRouting()
  configureCrm2CompanyRouting()
}
