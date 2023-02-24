package com.dilivva.realtime

import io.ktor.client.engine.cio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

@Suppress("unused")
object Realtime {
    private val data = Data(CIO.create())
    private var isConnected = false
    fun configureApp(baseurl: String,  username: String, path: String){
        data.configure(baseurl, username, path)
    }
    fun configureApp(baseurl: String,  username: String){
        data.configure(baseurl, username, "connect")
    }
    fun connectWithCallback(scope: CoroutineScope, onResponse: (Response) -> Unit){
        connectNow(scope, onResponse)
    }
    fun connect(scope: CoroutineScope){
        connectNow(scope)
    }
    private fun connectNow(
        scope: CoroutineScope,
        onResponse: (Response) -> Unit = {}
    ){
        if (!isConnected) {
            isConnected = true
            data.connectToServer()
                .onEach { onResponse(Response.Message(it)) }
                .catch { onResponse(Response.Error(it.stackTraceToString())) }
                .onCompletion { isConnected = false }
                .flowOn(Dispatchers.IO).launchIn(scope)
        }
    }
    suspend fun sendData(coordinates: Coordinates){
        if (isConnected) {
            data.send(coordinates)
        }
    }
}

sealed class Response{
    data class Error(val error: String): Response()
    data class Message(val message: String): Response()
}