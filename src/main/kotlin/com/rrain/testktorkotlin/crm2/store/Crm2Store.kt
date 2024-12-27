package com.rrain.testktorkotlin.crm2.store


data object Crm2Store {
  
  
  interface WithIdAndPropsMap {
    val map: MutableMap<String, Any?>
    var id: String
  }
  
  
  
  data class Reference(
    var id: String,
    var fio: String,
    var name: String,
    var agreementNumber: Int,
    var type: String?,
    var sum: String,
    var completed: String, // 'true' | 'false'
    var statement: String?, // 'Заявление'... | null
    var date: String,
    var fieldDetails1: String,
    var fieldDetails2: String,
    var fieldDetails3: String,
  )
  val referenceList = mutableListOf(
    Reference(
      id = "6",
      fio = "Степанов Степан Степанович",
      name = "Кредит № 6",
      agreementNumber = 200000003,
      type = null,
      sum = "14000",
      statement = null,
      completed = "false",
      date = "2021-10-05T14:48:00.000Z",
      fieldDetails1 = "Детали 1",
      fieldDetails2 = "Детали 2",
      fieldDetails3 = "Детали 3",
    )
  )
  
  
  
  
  
  
  
  
  data class Legal (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var name: String by map
  }
  val Legals = mutableListOf(
    Legal(mutableMapOf(
      "id" to "IdLegal[1]",
      "name" to "Юр лицо 1",
    )),
    Legal(mutableMapOf(
      "id" to "IdLegal[2]",
      "name" to "Юр лицо 2",
    )),
    Legal(mutableMapOf(
      "id" to "IdLegal[3]",
      "name" to "Юр лицо 3",
    )),
  )
  
  
  
  data class Brand (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var name: String by map
  }
  val Brands = mutableListOf(
    Brand(mutableMapOf(
      "id" to "IdBrand[1]",
      "name" to "Бренд 1",
    )),
    Brand(mutableMapOf(
      "id" to "IdBrand[2]",
      "name" to "Бренд 2",
    )),
    Brand(mutableMapOf(
      "id" to "IdBrand[3]",
      "name" to "Бренд 3",
    )),
  )
  
  
  
  data class Settlement (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var name: String by map
  }
  val Settlements = mutableListOf(
    Settlement(mutableMapOf(
      "id" to "IdSettlement[1]",
      "name" to "Тольятти",
    )),
    Settlement(mutableMapOf(
      "id" to "IdSettlement[2]",
      "name" to "Екатеринбург",
    )),
    Settlement(mutableMapOf(
      "id" to "IdSettlement[3]",
      "name" to "Иркутск",
    )),
  )
  
  
  
  
  data class Curator (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var firstName: String by map
    var lastName: String by map
    var patronymic: String by map
    
    val fullName get() = "$lastName $firstName $patronymic"
  }
  val Curators = mutableListOf(
    Curator(mutableMapOf(
      "id" to "IdCurator[1]",
      "firstName" to "Иван",
      "lastName" to "Иванов",
      "patronymic" to "Иванович",
    )),
    Curator(mutableMapOf(
      "id" to "IdCurator[2]",
      "firstName" to "Сергей",
      "lastName" to "Сергеев",
      "patronymic" to "Сергеевич",
    )),
    Curator(mutableMapOf(
      "id" to "IdCurator[3]",
      "firstName" to "Дмитрий",
      "lastName" to "Дмитриев",
      "patronymic" to "Дмитриевич",
    )),
  )
  
  
  
  data class Technology (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var name: String by map
  }
  val Technologies = mutableListOf(
    Technology(mutableMapOf(
      "id" to "IdTechnology[1]",
      "name" to "Телефонная",
    )),
    Technology(mutableMapOf(
      "id" to "IdTechnology[2]",
      "name" to "Технология 2",
    )),
    Technology(mutableMapOf(
      "id" to "IdTechnology[3]",
      "name" to "Технология 3",
    )),
  )
  
  
  
  data class Sale (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var price: String by map
  }
  val Sales = mutableListOf(
    Sale(mutableMapOf(
      "id" to "IdSale[1]",
      "price" to "5567.00",
    )),
    Sale(mutableMapOf(
      "id" to "IdSale[2]",
      "price" to "12345.00",
    )),
    Sale(mutableMapOf(
      "id" to "IdSale[3]",
      "price" to "867564.00",
    )),
    Sale(mutableMapOf(
      "id" to "IdSale[4]",
      "price" to "98764.00",
    )),
    Sale(mutableMapOf(
      "id" to "IdSale[5]",
      "price" to "76565.00",
    )),
  )
  
  
  
  data class Segment (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var name: String by map
  }
  val Segments = mutableListOf(
    Segment(mutableMapOf(
      "id" to "IdSegment[1]",
      "name" to "Массажное оборудование",
    )),
    Segment(mutableMapOf(
      "id" to "IdSegment[2]",
      "name" to "Окна ПВХ",
    )),
    Segment(mutableMapOf(
      "id" to "IdSegment[3]",
      "name" to "Посудомоечные машины",
    )),
  )
  
  
  
  
  
  
  enum class Status {
    GREEN, YELLOW, RED, GREY
  }
  data class Company (override val map: MutableMap<String, Any?>) : WithIdAndPropsMap {
    override var id: String by map
    var uidTT: String by map
    var status: Status by map
    var inn: String by map
    var legalId: String by map
    var brandId: String by map
    var settlementId: String by map
    var address: String by map
    var curatorId: String by map
    var technologyId: String by map
    var maxDataSale: String? by map
    var companyDateConnect: String by map
    var establishmentDate: String by map
    var phoneTT: String by map
    var emailTT: String by map
    var gsto: String by map
    var problemsTT: String by map
  }
  var Companies = mutableListOf(
    Company(mutableMapOf(
      "id" to "IdCompanyShortInfo[1]",
      "uidTT" to "59",
      "status" to Status.GREEN,
      "inn" to "484851087955",
      "legalId" to Legals[0].id,
      "brandId" to Brands[0].id,
      "settlementId" to Settlements[0].id,
      "address" to "г Город 1, ул. Улица 1, дом 1",
      "curatorId" to Curators[0].id,
      "technologyId" to Technologies[0].id,
      "maxDataSale" to "2021-10-05",
      "companyDateConnect" to "2017-11-02",
      "establishmentDate" to "2017-11-02",
      "phoneTT" to "+71234568899",
      "emailTT" to "ggggg@mail.ru",
      "gsto" to "ГСТО",
      "problemsTT" to "Хьюстон, у нас пробемы",
    )),
    Company(mutableMapOf(
      "id" to "IdCompanyShortInfo[2]",
      "uidTT" to "2",
      "status" to Status.YELLOW,
      "inn" to "850276214359",
      "legalId" to Legals[1].id,
      "brandId" to Brands[1].id,
      "settlementId" to Settlements[0].id,
      "address" to "г Город 2, ул. Улица 2, дом 2",
      "curatorId" to Curators[1].id,
      "technologyId" to Technologies[1].id,
      "maxDataSale" to null,
      "companyDateConnect" to "2017-11-30",
      "establishmentDate" to "2017-11-30",
      "phoneTT" to "+71234568899",
      "emailTT" to "ggggg@mail.ru",
      "gsto" to "ГСТО",
      "problemsTT" to "Хьюстон, у нас пробемы",
    )),
    Company(mutableMapOf(
      "id" to "IdCompanyShortInfo[3]",
      "uidTT" to "3",
      "status" to Status.RED,
      "inn" to "850279994359",
      "legalId" to Legals[1].id,
      "brandId" to Brands[1].id,
      "settlementId" to Settlements[2].id,
      "address" to "г Город 3, ул. Улица 3, дом 3",
      "curatorId" to Curators[2].id,
      "technologyId" to Technologies[2].id,
      "maxDataSale" to "2022-11-06",
      "companyDateConnect" to "2019-08-06",
      "establishmentDate" to "2019-08-06",
      "phoneTT" to "+73334568899",
      "emailTT" to "ggggg@mail.ru",
      "gsto" to "ГСТО",
      "problemsTT" to "Хьюстон, у нас пробемы",
    )),
  )
  
  
  
  data class CompanyToSale (val map: MutableMap<String, Any?>) {
    var companyId: String by map
    var saleId: String by map
  }
  var CompanyToSales = mutableListOf(
    CompanyToSale(mutableMapOf(
      "companyId" to Companies[0].id,
      "saleId" to Sales[0].id,
    )),
    CompanyToSale(mutableMapOf(
      "companyId" to Companies[0].id,
      "saleId" to Sales[1].id,
    )),
    CompanyToSale(mutableMapOf(
      "companyId" to Companies[0].id,
      "saleId" to Sales[2].id,
    )),
    CompanyToSale(mutableMapOf(
      "companyId" to Companies[1].id,
      "saleId" to Sales[3].id,
    )),
    CompanyToSale(mutableMapOf(
      "companyId" to Companies[1].id,
      "saleId" to Sales[4].id,
    )),
  )
  
  
  data class CompanyToSegment (val map: MutableMap<String, Any?>) {
    var companyId: String by map
    var segmentId: String by map
  }
  var CompanyToSegments = mutableListOf(
    CompanyToSegment(mutableMapOf(
      "companyId" to Companies[0].id,
      "segmentId" to Segments[0].id,
    )),
    CompanyToSegment(mutableMapOf(
      "companyId" to Companies[0].id,
      "segmentId" to Segments[1].id,
    )),
    CompanyToSegment(mutableMapOf(
      "companyId" to Companies[0].id,
      "segmentId" to Segments[2].id,
    )),
    CompanyToSegment(mutableMapOf(
      "companyId" to Companies[1].id,
      "segmentId" to Segments[0].id,
    )),
    CompanyToSegment(mutableMapOf(
      "companyId" to Companies[1].id,
      "segmentId" to Segments[1].id,
    )),
  )
  
  
  /*
  data class CompanyShort (
    var id: String,
    var uidTT: String,
    var inn: String,
    var nameLegal: String,
    var brand: String?,
    var settlement: String,
    var address: String,
    var maxDateSale: String?,
    var personCurator: String,
    var segments: MutableList<String>,
    var nameTechnology: String,
    var companyDateConnect: String,
  )
  val companyList = mutableListOf(
    CompanyShort(
      id = "IdCompanyShortInfo1",
      uidTT = "59",
      inn = "484851087955",
      nameLegal = "Компания 100669",
      //brand = "name 76e7e41c-9cf7-42a7-b30d-af6fe6ccd55f",
      brand = brandList[0].name,
      settlement = "Тольятти",
      //address = "address1747d099-127d-9ff4-53ec-c93743822222",
      address = "г. 1, улица 1, дом 1",
      maxDateSale = "2021-10-05T14:48:00.000Z",
      //personCurator = "last_name84861387-9750-415c-97db-dba0715a1582 first_name 84861387-9750-415c-97db-dba0715a1582 patronymic 84861387-9750-415c-97db-dba0715a1582",
      personCurator = "Иванов Иван Иванович",
      segments = mutableListOf("Массажное оборудование", "Сегмент 2"),
      nameTechnology = "Телефонная",
      companyDateConnect = "2017-11-30",
    ),
    CompanyShort(
      id = "IdCompanyShortInfo2",
      uidTT = "2",
      inn = "850276214359",
      nameLegal = "Компания 101810",
      //brand = null,
      brand = brandList[1].name,
      settlement = "Екатеринбург",
      //address = "addressf359eef4-671e-438e-8e8e-f8ddfdc7ec7f",
      address = "г. 2, улица 2, дом 2",
      maxDateSale = "2022-11-06T10:30:00.000Z",
      //personCurator = "last_namec74b0091-f05b-475c-8cc8-c6b053625a61 first_name c74b0091-f05b-475c-8cc8-c6b053625a61 patronymic c74b0091-f05b-475c-8cc8-c6b053625a61",
      personCurator = "Олегов Олег Олегович",
      segments = mutableListOf("Окна ПВХ", "Сегмент 3"),
      nameTechnology = "Телефонная",
      companyDateConnect = "2019-08-06",
    ),
  )*/
  
  
}