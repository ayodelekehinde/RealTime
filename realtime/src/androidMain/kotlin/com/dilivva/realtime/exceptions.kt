package com.dilivva.realtime

import java.nio.channels.UnresolvedAddressException
import kotlin.reflect.KClass

actual fun getExceptions(): List<KClass<out Exception>>{
    return listOf(UnresolvedAddressException::class)
}

actual fun Throwable.isNetworkError():Boolean{
    return this is UnresolvedAddressException
}