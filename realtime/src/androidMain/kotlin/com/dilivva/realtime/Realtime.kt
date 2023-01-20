package com.dilivva.realtime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object Realtime {

    fun connect(username: String, scope: CoroutineScope){
        connectToServer(username).onEach {
            println("Message=$it")
        }.catch {
            println("Error=${it.message}")
        }.launchIn(scope)
    }
    suspend fun sendData(data: String){
        send(message = data)
    }
}