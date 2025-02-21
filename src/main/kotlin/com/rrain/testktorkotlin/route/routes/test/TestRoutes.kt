package com.rrain.testktorkotlin.route.routes.test

import io.ktor.server.application.*


fun Application.configureTestRoutes() {
  configureJsonSerializationTestRoutes()
  configureStaticTestRoutes()
  configureImgTestRoutes()
}