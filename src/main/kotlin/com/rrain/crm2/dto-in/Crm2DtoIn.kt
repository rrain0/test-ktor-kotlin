package com.rrain.crm2.`dto-in`



data object Crm2DtoIn {
  
  
  
  enum class SortType {
    ASC, DESC
  }
  data class Order (
    val field: String,
    val sort_type: SortType,
  )
  data class Pageable (
    val page: Int,
    val size: Int,
  )
  data class Get (
    val order: Order,
    val pageable: Pageable,
    val filter: Map<String, List<String>?>,
  )
  
  
  val EmptyGet = Get(
    order = Order(field = "", sort_type = SortType.ASC),
    pageable = Pageable(page = 1, size = 3),
    filter = mapOf(),
  )
  
  
}

