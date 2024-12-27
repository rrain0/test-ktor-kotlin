package com.rrain.testktorkotlin.util



inline fun <reified T> Any?.cast(): T = this as T


fun println(vararg args: Any?) = kotlin.io.println(args.joinToString(" "))

