package com.rrain.util.`object`


infix fun String.inobj(obj: Any?): Boolean {
  if (obj == null) return false
  //return obj::class.memberProperties.any { it.name == this }
  return obj::class.members.any { it.name == this }
}


