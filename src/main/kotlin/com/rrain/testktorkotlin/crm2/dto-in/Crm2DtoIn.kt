package com.rrain.testktorkotlin.crm2.`dto-in`



data object Crm2DtoIn {
  
  
  
  enum class SortType {
    ASC, DESC
  }
  data class Order (
    val field: String,
    val sort_type: com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.SortType,
  )
  data class Pageable (
    val page: Int,
    val size: Int,
  )
  data class Get (
    val order: com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Order,
    val pageable: com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Pageable,
    val filter: Map<String, List<String>?>,
  )
  
  
  val EmptyGet = com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Get(
    order = com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Order(
      field = "",
      sort_type = com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.SortType.ASC
    ),
    pageable = com.rrain.testktorkotlin.crm2.`dto-in`.Crm2DtoIn.Pageable(page = 1, size = 3),
    filter = mapOf(),
  )
  
  
}

