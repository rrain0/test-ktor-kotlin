package com.rrain.testktorkotlin.route.routes.test

import com.rrain.util.fileutils.FileU
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay



fun Application.configureImgTestRoutes() {
  
  routing {
    
    // http://localhost:8081/test/image/ban.jpg
    get("/test/image/ban.jpg") {
      val banImgStream = FileU.getResourceAsStream("static/ban.jpg")
      
      call.caching = CachingOptions(CacheControl.NoStore(null))
      call.respondOutputStream(
        contentType = ContentType.parse("image/jpg"),
        status = HttpStatusCode.OK
      ) { banImgStream.transferTo(this) }
    }
    
    
    
    
    var imgError = false
    
    // http://localhost:8081/test/image/errorable/ban.jpg
    get("/test/image/errorable/ban.jpg") {
      
      if (imgError) {
        return@get call.respond(
          HttpStatusCode.InternalServerError,
          "Image error enabled"
        )
      }
      
      val banImgStream = FileU.getResourceAsStream("static/ban.jpg")
      
      call.caching = CachingOptions(CacheControl.NoStore(null))
      call.respondOutputStream(
        contentType = ContentType.parse("image/jpg"),
        status = HttpStatusCode.OK
      ) { banImgStream.transferTo(this) }
    }
    
    get("/test/image/errorable/enable-error") {
      imgError = true
      call.respondText("Image error enabled")
    }
    get("/test/image/errorable/disable-error") {
      imgError = false
      call.respondText("Image error disabled")
    }
    
    
    
    
    
    // http://localhost:8081/test/image/delay/ban.jpg
    get("/test/image/delay/ban.jpg") {
      
      delay(4000)
      
      val banImgStream = FileU.getResourceAsStream("static/ban.jpg")
      
      call.caching = CachingOptions(CacheControl.NoStore(null))
      call.respondOutputStream(
        contentType = ContentType.parse("image/jpg"),
        status = HttpStatusCode.OK
      ) { banImgStream.transferTo(this) }
    }
    
    // http://localhost:8081/test/image/delay-error-404/ban.jpg
    get("/test/image/delay-error-404/ban.jpg") {
      delay(4000)
      return@get call.respond(HttpStatusCode.NotFound)
    }
    
    // http://localhost:8081/test/image/delay-error-500/ban.jpg
    get("/test/image/delay-error-500/ban.jpg") {
      delay(4000)
      return@get call.respond(HttpStatusCode.InternalServerError)
    }
    
    
    
  }
  
}