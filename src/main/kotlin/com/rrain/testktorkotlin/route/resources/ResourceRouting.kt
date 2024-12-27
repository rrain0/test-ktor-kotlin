package com.rrain.testktorkotlin.route.resources

import com.rrain.testktorkotlin.route.resources.articles.configureArticlesRouting
import io.ktor.server.resources.Resources
import io.ktor.server.application.*


fun Application.configureResourceRouting() {
  
  // Plugin for using of Typed Routes (Resources)
  install(Resources)
  
  configureArticlesRouting()
  
}
