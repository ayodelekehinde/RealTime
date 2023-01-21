package com.dilivva.realtime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object Realtime {


    fun configureApp(baseurl: String,  username: String, path: String){
        configure(baseurl, username, path)
    }
    fun configureApp(baseurl: String,  username: String){
        configure(baseurl, username, "connect")
    }
    fun connectWithCallback(scope: CoroutineScope, onSuccess: (String) -> Unit, onError: (String?) -> Unit){
        connectNow(scope, onSuccess, onError)
    }
    fun connect(scope: CoroutineScope){
        connectNow(scope)
    }
    private fun connectNow(
        scope: CoroutineScope,
        onSuccess: (String) -> Unit = {},
        onError: (String?) -> Unit = {}
    ){
        connectToServer().onEach {
            onSuccess(it)
        }.catch {
            onError(it.stackTraceToString())
        }.launchIn(scope)
    }
    suspend fun sendData(coordinates: Coordinates){
        send(coordinates)
    }
}