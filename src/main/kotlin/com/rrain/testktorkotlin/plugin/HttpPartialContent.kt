package com.rrain.testktorkotlin.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.partialcontent.*

fun Application.configureHttpPartialContent() {
  
  
  //https://ktor.io/docs/server-partial-content.html
  
  /*
    The PartialContent plugin adds support for handling HTTP range requests
    used to send only a portion of an HTTP message back to a client.
    This plugin is useful for streaming content or resuming partial downloads.
  
    PartialContent has the following limitations:
  
    ● Works only for HEAD and GET requests and returns 405 Method Not Allowed
      if the client tries to use the Range header with other methods.
    ● Works only for responses that have the Content-Length header defined.
    ● Disables Compression when serving ranges.
  
   */
  
  
  // The PartialContent plugin can also be installed to specific routes.
  // This might be useful if you need different PartialContent configurations for different application resources.
  install(PartialContent) {
    // Maximum number of ranges that will be accepted from an HTTP request.
    // If the HTTP request specifies more ranges, they will all be merged into a single range.
    maxRangeCount = 10
  }
  
}
