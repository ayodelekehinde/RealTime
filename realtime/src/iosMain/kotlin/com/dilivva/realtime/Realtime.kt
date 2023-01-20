package com.dilivva.realtime

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import platform.posix.err

private val scope = MainScope()


fun connect(onSuccess: (String) -> Unit, onError: (String?) -> Unit) {
    connectToServer("iOS").onEach {
        onSuccess(it)
    }.catch {
        onError(it.message)
    }.onCompletion {
        it?.message?.let { error ->
            onError("onComplete: $error")
        }
    }.launchIn(scope)
}

fun sendMessage(msg: String) {
    scope.launch {
        send(message = msg)
    }
}
