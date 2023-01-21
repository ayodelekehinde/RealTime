package com.dilivva.realtime

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import platform.posix.err

private val scope = MainScope()


fun configureApp(baseurl: String,  username: String, path: String){
    configure(baseurl, username, path)
}
fun configureApp(baseurl: String,  username: String){
    configure(baseurl, username, "connect")
}
fun connect(onSuccess: (String) -> Unit, onError: (String?) -> Unit) {
    connectToServer().onEach {
        onSuccess(it)
    }.catch {
        onError(it.message)
    }.onCompletion {
        it?.message?.let { error ->
            onError("onComplete: $error")
        }
    }.launchIn(scope)
}
fun connect() {
    connectToServer().onEach {
        println(it)
    }.catch {
        println(it.stackTraceToString())
    }.onCompletion {
        it?.message?.let { error ->
            println(error)
        }
    }.launchIn(scope)
}

fun sendMessage(coordinates: Coordinates) {
    scope.launch {
        send(coordinates)
    }
}
