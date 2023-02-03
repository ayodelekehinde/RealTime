package com.dilivva.realtime

import io.ktor.client.engine.darwin.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import platform.posix.err

private val scope = MainScope()
private val data = Data(Darwin.create())

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
    data.connectToServer().onEach {
        println(it)
    }.catch {
        println(it.stackTraceToString())
    }.onCompletion {
        it?.message?.let { error ->
            println(error)
        }
    }.flowOn(Dispatchers.Default).launchIn(scope)
}

fun sendMessage(coordinates: Coordinates) {
    scope.launch {
        data.send(coordinates)
    }
}
