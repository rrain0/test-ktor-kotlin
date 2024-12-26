package com.rrain.plugin

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.forwardedheaders.*

fun Application.configureHTTP() {
  install(ForwardedHeaders) // WARNING: for security, do not include this if not behind a reverse proxy
  install(XForwardedHeaders) // WARNING: for security, do not include this if not behind a reverse proxy
  install(DefaultHeaders) {
    header("X-Engine", "Ktor") // will send this header with each response
  }
  install(CORS) {
    
    allowCredentials = true
    
    allowMethod(HttpMethod.Get) // Fetches the state of a resource without altering the system
    allowMethod(HttpMethod.Post) // Creates a new resource without saying where
    allowMethod(HttpMethod.Put) // Replaces an existing resource, overwriting whatever else (if anything) is already there
    allowMethod(HttpMethod.Delete) // Removes an existing resource
    allowMethod(HttpMethod.Patch) // Alters an existing resource (partially rather than creating a new resource)
    allowMethod(HttpMethod.Options)
    allowMethod(HttpMethod.Head)
    
    allowHeader(HttpHeaders.Authorization)
    allowHeader(HttpHeaders.ContentType)
    //allowHeader("MyCustomHeader") // allow custom header
    
    maxAgeInSeconds = 2 * 60 * 60 // results of preflight request will be valid for 2 hours
    
    //allowHost("localhost:5173", listOf("http","https"))
    
    anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
  }
}
