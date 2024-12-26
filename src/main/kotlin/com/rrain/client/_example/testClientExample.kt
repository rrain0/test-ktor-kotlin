package com.rrain.client._example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.cio.*
import io.ktor.util.date.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File


@Suppress("UNREACHABLE_CODE")
private suspend fun testExample(client: HttpClient) {
  throw RuntimeException("Not for run purposes")
  
  var response: HttpResponse
  
  
  // METHOD
  // specify GET method
  response = client.request("https://ktor.io/") {
    method = HttpMethod.Get
  }
  
  // use GET function
  response = client.get("https://ktor.io/docs/welcome.html")
  
  
  // URL
  // build URL
  response = client.get {
    url {
      protocol = URLProtocol.HTTPS
      host = "ktor.io"
      path("docs/welcome.html")
    }
  }
  
  
  // PATH
  // encode & append path to URL
  response = client.get("https://ktor.io") {
    url {
      appendPathSegments("docs", "welcome.html")
    }
  }
  
  // append raw path to URL
  response = client.get("https://ktor.io") {
    url {
      appendEncodedPathSegments("docs", "welcome.html")
    }
  }
  
  
  // QUERY
  // encode & append query params
  response = client.get("https://ktor.io") {
    url {
      parameters.append("token", "abc123")
      // keep "?" if no query
      trailingQuery = true
    }
  }
  
  // append raw query params
  response = client.get("https://ktor.io") {
    url {
      encodedParameters.append("token", "abc123")
    }
  }
  
  
  // FRAGMENT
  // encode & append fragment (#)
  response = client.get("https://ktor.io") {
    url {
      fragment = "some_anchor"
    }
  }
  
  // append raw fragment (#)
  response = client.get("https://ktor.io") {
    url {
      encodedFragment = "some_anchor"
    }
  }
  
  
  // HEADERS
  response = client.get("https://ktor.io") {
    headers {
      append(HttpHeaders.Accept, "text/html")
      append(HttpHeaders.Authorization, "abc123")
      append(HttpHeaders.UserAgent, "ktor client")
    }
    basicAuth("username", "password")
    bearerAuth("token")
  }
  
  
  // COOKIES
  client.get("https://ktor.io") {
    cookie(name = "user_name", value = "jetbrains", expires = GMTDate(
      seconds = 0,
      minutes = 0,
      hours = 10,
      dayOfMonth = 1,
      month = Month.APRIL,
      year = 2023
    ))
  }
  // Ktor also provides the HttpCookies plugin that allows you to keep cookies between calls.
  // If this plugin is installed, cookies added using the cookie function are ignored.
  
  
  // BODY
  // Raw string
  response = client.post("http://localhost:8080/post") {
    setBody("Body content")
  }
  
  // Object - available via ContentNegotiation plugin
  data class Customer(val id: Int, val name: String, val secondName: String)
  response = client.post("http://localhost:8080/customer") {
    contentType(ContentType.Application.Json)
    setBody(Customer(3, "Jet", "Brains"))
  }
  
  // Form
  response = client.submitForm(
    url = "http://localhost:8080/signup",
    formParameters = parameters {
      append("username", "JetBrains")
      append("email", "example@jetbrains.com")
      append("password", "foobar")
      append("confirmation", "foobar")
    },
    // Can encode form into query
    encodeInQuery = true
  )
  
  // Form - Binary data
  response = client.submitFormWithBinaryData(
    url = "http://localhost:8080/upload",
    formData = formData {
      append("description", "Ktor logo")
      append("image", File("ktor_logo.png").readBytes(), Headers.build {
        append(HttpHeaders.ContentType, "image/png")
        append(HttpHeaders.ContentDisposition, """filename="ktor_logo.png"""")
      })
    }
  )
  
  // Form - Multipart data
  // MultiPartFormDataContent also allows you to override a boundary and content type as follows:
  fun customMultiPartMixedDataContent(parts: List<PartData>): MultiPartFormDataContent {
    val boundary = "WebAppBoundary"
    val contentType = ContentType.MultiPart.Mixed.withParameter("boundary", boundary)
    return MultiPartFormDataContent(parts, boundary, contentType)
  }
  
  // Binary data
  response = client.post("http://0.0.0.0:8080/upload") {
    setBody(File("ktor_logo.png").readChannel())
  }
  
  // RESPONSE
  val customer: Customer = client.get("http://localhost:8080/customer/3").body()
}



private fun testRequests(client: HttpClient) {
  
  // Parallel requests
  runBlocking {
    // Parallel requests
    val firstRequest: Deferred<String> = async { client.get("http://localhost:8080/path1").bodyAsText() }
    val secondRequest: Deferred<String> = async { client.get("http://localhost:8080/path2").bodyAsText() }
    val firstRequestContent = firstRequest.await()
    val secondRequestContent = secondRequest.await()
  }
  
  // Cancel a request
  runBlocking {
    val job = launch {
      val requestContent: String = client.get("http://localhost:8080").bodyAsText()
    }
    job.cancel()
  }
  
}

