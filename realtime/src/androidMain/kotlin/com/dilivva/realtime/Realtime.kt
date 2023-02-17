package com.dilivva.realtime

import io.ktor.client.engine.cio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

@Suppress("unused")
object Realtime {
    private val data = Data(CIO.create())
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
    fun isConnected() = isConnected
    private fun connectNow(
        scope: CoroutineScope,
        onResponse: (Response) -> Unit = {}
    ){
        data.connectToServer().onEach {
            onResponse(Response.Message(it))
        }.catch {
            onResponse(Response.Error(it.stackTraceToString()))
        }.flowOn(Dispatchers.IO).launchIn(scope)
    }
    suspend fun sendData(coordinates: Coordinates){
        data.send(coordinates)
    }
}

sealed class Response{
    data class Error(val error: String): Response()
    data class Message(val message: String): Response()
}