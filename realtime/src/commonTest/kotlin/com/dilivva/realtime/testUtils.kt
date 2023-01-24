package com.dilivva.realtime

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

suspend fun <T> Flow<T>.unBox(): T?{
    var value: T? = null
    collectLatest {
        value =  it
    }
    return value
}