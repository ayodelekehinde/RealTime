package com.dilivva.realtime

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.utils.io.core.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

private val client = HttpClient {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}
private val channel = Channel<String>()
private val flow = MutableStateFlow("")

private val sendMessageFlow: MutableSharedFlow<String> = MutableSharedFlow()


/**
 * Make customizable
 * 1. pass base url, path and parameter
 *
 */

private var socket: DefaultWebSocketSession? = null

internal var isConnected = false

//internal suspend fun connectToServer() {
//
//    println("Connecting to server")
//    client.use {
//        it.wss("wss://ktorrealtimetest-production.up.railway.app/connect/ayodele") {
//            socket = this
//            try {
//                println("Connected")
//                while (true){
//                    val frame = incoming.receive()
//                }
//            } catch (e: ClosedReceiveChannelException) {
//                println("Closed=${e.message}")
//            }
//        }
//    }
//}
internal fun connectToServer(username: String) = flow {
    if (!isConnected) {
        client.wss("wss://ktorrealtimetest-production.up.railway.app/connect/$username") {
            println("Connecting")
            coroutineScope {
                channel.consumeAsFlow()
                    .map(::send)
                    .launchIn(this)
                while (true) {
                    println("Connected")
                    isConnected = true
                    val frame = incoming.receive()
                    if (frame is Frame.Text) {
                        emit(frame.readText())
                    }
                }
            }
        }
    }else{
        println("Couldnt connect")
    }
}.retryWithBackoff()

internal suspend fun send(message: String) {
    //println("Message=$message")
    channel.send(message)
    //socket?.send(message)
}



