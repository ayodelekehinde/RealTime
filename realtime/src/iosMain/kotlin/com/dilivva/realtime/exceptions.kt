package com.dilivva.realtime

import io.ktor.client.engine.darwin.*
import kotlin.reflect.KClass

actual fun getExceptions(): List<KClass<out Exception>>{
    return listOf(DarwinHttpRequestException::class)
}
actual fun Throwable.isNetworkError():Boolean{
    return this is DarwinHttpRequestException
}