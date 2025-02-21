package com.rrain.testktorkotlin.route.routes.test

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths


// Serves static files from resources
fun Application.configureStaticTestRoutes() {
  
  routing {
    
    // serves static content from application resources folder:
    // <project-folder>/src/main/resources
    staticResources(
      // http://localhost:8081/static/......
      // http://localhost:8081/static/index.html
      // http://localhost:8081/static/ban.jpg
      "/static", // url base path
      // <project-folder>/src/main/resources/static
      "static" // subfolder in resources folder
    ) {
      enableAutoHeadResponse()
      // Additionally you can setup default, cacheControl, contentType, modify call directly
    }
    
    
    // Any request from the root URL / is mapped directly
    // to the contents of the ZIP file text-files.zip in file system.
    /*staticZip("/", "", Paths.get("files/text-files.zip")) {
      enableAutoHeadResponse()
    }*/
    
    
    
    // Serves static content from file system relative application working directory:
    // <project-folder>
    staticFiles(
      // http://localhost:8081/static-files/......
      // http://localhost:8081/static-files/ban.jpg
      // http://localhost:8081/static-files/index.html
      "/static-files",
      // <project-folder>/build/resources/main/static
      File("build/resources/main/static")
    ) {
      enableAutoHeadResponse()
    }
    
    // working directory is project root:
    // D:\PROG\Kotlin\Projects\kupidon-ktor-kotlin
    //println("working dir: ${File("").absolutePath}")
    
  }
  
}