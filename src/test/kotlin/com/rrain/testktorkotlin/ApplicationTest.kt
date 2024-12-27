package com.rrain.testktorkotlin

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
  @Test
  fun testRoot() = testApplication {
    
    /*
      To explicitly load modules, use the application function within testApplication.
      This approach allows you to manually specify which modules to load,
      providing greater control over your test setup.
    */
    //application {
    //  module()
    //}
    
    /*
      If you want to load modules from a configuration file, use the environment function to specify the configuration file for your test.
    */
    environment {
      // You can specify separate configuration file
      config = ApplicationConfig("application.conf")
    }
    
    client.get("/ktor/hello").apply {
      assertEquals(HttpStatusCode.OK, status)
      assertEquals("Hello Ktor!", bodyAsText())
    }
    
  }
}
