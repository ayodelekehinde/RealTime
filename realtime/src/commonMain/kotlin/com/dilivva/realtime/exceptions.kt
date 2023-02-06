package com.dilivva.realtime

import kotlin.reflect.KClass

expect fun getExceptions() : List<KClass<out Exception>>

expect fun Throwable.isNetworkError(): Boolean


object UnauthenticatedException: Exception("Unauthenticated")