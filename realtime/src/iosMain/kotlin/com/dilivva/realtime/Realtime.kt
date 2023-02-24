package com.dilivva.realtime

import io.ktor.client.engine.darwin.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private val scope = MainScope()
private val data = Data(Darwin.create())
private var isConnected = false

fun configureApp(baseurl: String,  username: String, path: String){
    data.configure(baseurl, username, path)
}
fun configureApp(baseurl: String,  username: String){
    data.configure(baseurl, username, "connect")
}
fun connect(onSuccess: (String) -> Unit, onError: (String?) -> Unit) {
    connectNow(onSuccess, onError)
}
fun connect() {
    connectNow()
}
private fun connectNow(
    onSuccess: (String) -> Unit = {},
    onError: (String?) -> Unit = {}
) {
    if (!isConnected) {
        isConnected = true
        data.connectToServer()
            .onEach { onSuccess(it) }
            .catch { onError(it.stackTraceToString()) }
            .onCompletion { isConnected = false }
            .flowOn(Dispatchers.Default).launchIn(scope)
    }
}

fun sendMessage(coordinates: Coordinates) {
    scope.launch {
        if (isConnected) {
            data.send(coordinates)
        }
    }
}
