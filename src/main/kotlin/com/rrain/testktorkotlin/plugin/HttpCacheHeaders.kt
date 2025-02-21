package com.rrain.testktorkotlin.plugin

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureHttpCacheHeaders() {
  
  // "max-age" is preferable upon "expires" in cache control
  
  // "no-cache" means that cache is always stale + browser must ask server if it can reuse this cache
  // "must-revalidate" means that after cache becomes stale, browser must ask server if it can reuse this cache
  // "no-cache" = "max-age=0, must-revalidate"
  
  // "no-cache" - Эта директива означает, что кэшированные версии запрошенного ресурса нельзя использовать
  //              без предварительной проверки наличия обновленной версии. Обычно это делается с помощью ETag.
  // "no-store" - Ответ с директивой no-store нельзя кэшировать нигде и никогда.
  
  // Global cache control
  // Exception: Installing RouteScopedPlugin to application and route is not supported. Consider moving application level install to routing root.
  /*
  install(CachingHeaders) {
    options { call, outgoingContent ->
      when (outgoingContent.contentType?.withoutParameters()) {
        ContentType.Text.Plain -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 3600))
        ContentType.Text.Html -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60))
        ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
        else -> null
      }
    }
  }
  */
  
  // Root routing cache control
  routing {
    install(CachingHeaders) {
      options { call, outgoingContent ->
        when (outgoingContent.contentType?.withoutParameters()) {
          // !!! These options will be combined with options of descendant routes if not explicitly overriden
          //ContentType.Text.Plain -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 3600))
          ContentType.Text.Html -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60))
          ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
          else -> null
        }
      }
    }
  }
  
  
  // Route-level cache control
  routing {
    route("/test/cache/route-level") {
      install(CachingHeaders) {
        options { call, content -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = Int.MAX_VALUE)) }
      }
      get {
        call.respondText("Route-level cached")
      }
    }
  }
  
  
  // Call-level cache control
  routing {
    get("/test/cache/call-level") {
      // !!! install(CachingHeaders) is necessary elsewhere
      // Cache-Control: max-age=3600
      call.caching = CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 3600))
      call.respondText("Call-level cached")
    }
  }
  
  
  routing {
    
    // Private cache - stored only for current client in browser (for browser user)
    get("/test/cache/private") {
      // Cache-Control: max-age=3600, private
      call.caching = CachingOptions(CacheControl.MaxAge(
        maxAgeSeconds = 3600,
        visibility = CacheControl.Visibility.Private
      ))
      call.respondText("Private cache")
    }
    
    // Shared cache - located between server and client
    // Shared caches can be:
    // 1) Proxy caches (proxy services)
    // 2) Managed caches (caches you can control: reverse proxy, CDN, service worker + Cache API, ...).
    //    These caches can intentionally ignore standard directives or have theirs own directives.
    get("/test/cache/public") {
      // Cache-Control: max-age=3600, public
      call.caching = CachingOptions(CacheControl.MaxAge(
        maxAgeSeconds = 3600,
        visibility = CacheControl.Visibility.Public,
      ))
      call.respondText("Public cache")
    }
    
    get("/test/cache/no-cache") {
      // Cache-Control: no-cache
      call.caching = CachingOptions(CacheControl.NoCache(null))
      call.respondText("No cache")
    }
    
    get("/test/cache/no-store") {
      // Cache-Control: no-store
      call.caching = CachingOptions(CacheControl.NoStore(null))
      call.respondText("No store")
    }
    
    get("/test/cache/revalidate") {
      // Cache-Control: max-age=3600, s-maxage=1800, must-revalidate, proxy-revalidate
      call.caching = CachingOptions(CacheControl.MaxAge(
        maxAgeSeconds = 3600,
        proxyMaxAgeSeconds = 1800,
        proxyRevalidate = true,
        mustRevalidate = true,
      ))
      call.respondText("Revalidate")
    }
    
    
  }
  
}
