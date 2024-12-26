package com.rrain.crm2.`dto-out`

import com.rrain.crm2.`dto-in`.Crm2DtoIn
import com.rrain.crm2.store.Crm2Store
import com.rrain.crm2.store.Crm2Store.Brands
import com.rrain.crm2.store.Crm2Store.CompanyToSales
import com.rrain.crm2.store.Crm2Store.CompanyToSegments
import com.rrain.crm2.store.Crm2Store.Curators
import com.rrain.crm2.store.Crm2Store.Legals
import com.rrain.crm2.store.Crm2Store.Sales
import com.rrain.crm2.store.Crm2Store.Segments
import com.rrain.crm2.store.Crm2Store.Settlements
import com.rrain.crm2.store.Crm2Store.Technologies
import java.math.BigDecimal


data object Crm2DtoOut {
  
  enum class FilterType {
    EQUALS, CONTAINS, BETWEEN,
    EQUALS_IN_LIST,
    PROP_EQUALS, /*PROP_CONTAINS,*/
  }
  
  data class Prop(
    val filterType: FilterType,
  )
  
  val CompanyShortProps = mapOf(
    "id" to Prop(FilterType.EQUALS),
    
    "uidTT" to Prop(FilterType.CONTAINS),
    "status" to Prop(FilterType.EQUALS),
    
    "inn" to Prop(FilterType.CONTAINS),
    
    "legalId" to Prop(FilterType.EQUALS),
    "nameLegal" to Prop(FilterType.CONTAINS),
    
    "brandId" to Prop(FilterType.EQUALS),
    "brand" to Prop(FilterType.CONTAINS),
    
    "settlementId" to Prop(FilterType.EQUALS),
    "settlement" to Prop(FilterType.CONTAINS),
    
    "address" to Prop(FilterType.CONTAINS),
    
    "curatorId" to Prop(FilterType.EQUALS),
    "personCurator" to Prop(FilterType.CONTAINS),
    
    "technologyId" to Prop(FilterType.EQUALS),
    "nameTechnology" to Prop(FilterType.CONTAINS),
    
    "maxDataSale" to Prop(FilterType.BETWEEN),
    
    "companyDateConnect" to Prop(FilterType.BETWEEN),
    
    "saleIds" to Prop(FilterType.EQUALS_IN_LIST),
    "salesSum" to Prop(FilterType.CONTAINS),
    
    "establishmentDate" to Prop(FilterType.BETWEEN),
    "phoneTT" to Prop(FilterType.CONTAINS),
    "emailTT" to Prop(FilterType.CONTAINS),
    "gsto" to Prop(FilterType.CONTAINS),
    "problemsTT" to Prop(FilterType.CONTAINS),
    
    "segments" to Prop(FilterType.PROP_EQUALS),
  )
  
  data class CompanyShortDto (val map: MutableMap<String, Any?>) {
    val id: String by map
    val uidTT: String by map
    val status: Crm2Store.Status by map
    val inn: String by map
    
    val legalId: String by map
    val nameLegal: String by map
    
    val brandId: String by map
    val brand: String by map
    
    val settlementId: String by map
    val settlement: String by map
    
    val address: String by map
    
    val curatorId: String by map
    val personCurator: String by map
    
    val technologyId: String by map
    val nameTechnology: String by map
    
    val maxDataSale: String by map
    
    val companyDateConnect: String by map
    
    val saleIds: List<String> by map
    val salesSum: String by map
    
    val establishmentDate: String by map
    val phoneTT: String by map
    val emailTT: String by map
    val gsto: String by map
    val problemsTT: String by map
    
    val segments: List<Crm2Store.Segment> by map
  }
  fun List<Crm2Store.Company>.toDto(getDtoIn: Crm2DtoIn.Get? = null): List<CompanyShortDto> {
    return this
      .map {
        val legal = Legals.firstOrNull { legal -> legal.id == it.legalId }!!
        val brand = Brands.firstOrNull { brand -> brand.id == it.brandId }!!
        val settlement = Settlements.firstOrNull { settlement -> settlement.id == it.settlementId }!!
        val curator = Curators.firstOrNull { curator -> curator.id == it.curatorId }!!
        val technology = Technologies.firstOrNull { technology -> technology.id == it.technologyId }!!
        val sales = CompanyToSales
          .filter { cts -> cts.companyId == it.id }
          .map { cts -> Sales.find { sale -> sale.id == cts.saleId }!! }
        val segments = CompanyToSegments
          .filter { cts -> cts.companyId == it.id }
          .map { cts -> Segments.find { segment -> segment.id == cts.segmentId }!! }
        
        CompanyShortDto(mutableMapOf(
          "id" to it.id,
          "uidTT" to it.uidTT,
          "status" to it.status,
          "inn" to it.inn,
          
          "legalId" to legal.id,
          "nameLegal" to legal.name,
          
          "brandId" to brand.id,
          "brand" to brand.name,
          
          "settlementId" to settlement.id,
          "settlement" to settlement.name,
          
          "address" to it.address,
          
          "curatorId" to curator.id,
          "personCurator" to curator.fullName,
          
          "technologyId" to technology.id,
          "nameTechnology" to technology.name,
          
          "maxDataSale" to it.maxDataSale,
          
          "companyDateConnect" to it.companyDateConnect,
          
          "saleIds" to sales.map { sale -> sale.id },
          "salesSum" to sales.sumOf { sale -> BigDecimal(sale.price) }.toPlainString(),
          
          "establishmentDate" to it.establishmentDate,
          "phoneTT" to it.phoneTT,
          "emailTT" to it.emailTT,
          "gsto" to it.gsto,
          "problemsTT" to it.problemsTT,
          
          "segments" to segments,
        ))
      }
      .filter {
        var pass = true
        getDtoIn?.filter?.forEach { (prop, fValues) ->
          val fType = CompanyShortProps[prop]
          if (!fValues.isNullOrEmpty() && fType != null) {
            val v = it.map[prop]
            println("fValues: $fValues")
            when (fType.filterType) {
              FilterType.EQUALS -> {
                if (v !in fValues) pass = false
              }
              FilterType.CONTAINS -> {
                if (!fValues.any { fValue -> v is String && v.contains(fValue.trim(), ignoreCase = true) }) {
                  pass = false
                }
              }
              FilterType.BETWEEN -> {
                val s = fValues.getOrElse(0) { "" }
                val e = fValues.getOrElse(1) { "" }
                if (v is String && !s.isNullOrEmpty() && v < s) pass = false
                if (v is String && !e.isNullOrEmpty() && v > e) pass = false
              }
              
              FilterType.EQUALS_IN_LIST -> {
                if (v is List<*> && fValues.all { fValue -> fValue !in v }) {
                  pass = false
                }
              }
              
              /*FilterType.PROP_CONTAINS -> {
                if (v is List<*> && fValues.size >=2){
                  if (v.isEmpty()) {
                    pass = false
                  }
                  else {
                    val fProp = fValues.first()
                    val fValues = fValues.subList(0, fValues.size)
                    if (v[0] is Crm2Store.WithPropsMap) {
                      val v = v.map { (it as Crm2Store.WithPropsMap).map[fProp] }
                      if (
                        fValues.all { fValue -> fValue !in v }
                      ) {
                        pass = false
                      }
                    }
                  }
                }
              }*/
              
              FilterType.PROP_EQUALS -> {
                if (v is List<*> && fValues.size >=2){
                  if (v.isEmpty()) {
                    pass = false
                  }
                  else {
                    val fProp = fValues.first()
                    val fValues = fValues.subList(0, fValues.size)
                    if (v[0] is Crm2Store.WithIdAndPropsMap) {
                      val v = v.map { (it as Crm2Store.WithIdAndPropsMap).map[fProp] }
                      if (
                        fValues.all { fValue -> fValue !in v }
                      ) {
                        pass = false
                      }
                    }
                  }
                }
              }
            }
          }
        }
        pass
      }
  }
  
  
  
}

