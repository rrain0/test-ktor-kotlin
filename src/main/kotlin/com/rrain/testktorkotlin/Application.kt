package com.rrain.testktorkotlin

import com.rrain.testktorkotlin.crm2.route.*
import com.rrain.testktorkotlin.plugin.*
import com.rrain.testktorkotlin.route.configureRouting
import io.ktor.server.application.*



fun main(args: Array<String>) {
  io.ktor.server.jetty.jakarta.EngineMain.main(args)
}

fun Application.module() {
  configureLogging()
  configureJsonSerialization()
  
  configureHttpForwardedHeaders()
  configureHttpCacheHeaders()
  configureHttpCors()
  configureHttpPartialContent()
  configureHttpDefaultHeaders()
  configureHttpAutoHeadResponse()
  configureStatusPages()
  
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
