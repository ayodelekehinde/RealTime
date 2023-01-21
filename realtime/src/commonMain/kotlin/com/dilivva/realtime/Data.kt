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


fun configure(_baseurl: String,  _username: String, _path: String = "connect"){
    baseurl = _baseurl
    username = _username
    path = _path
}

private val client = HttpClient {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

@kotlinx.serialization.Serializable
data class Coordinates(
    val lat: String,
    val long: String
)
private val channel = Channel<String>()

private val serializableChannel = Channel<Coordinates>()


/**
 * Make customizable
 * 1. pass base url, path and parameter
 *
 */



internal var isConnected = false
private var baseurl = ""
private var path = ""
private var username = ""
internal fun connectToServer() = flow {
    require(baseurl.isNotEmpty()){ "BaseUrl is required, did you call initialize before connect" }
    require(path.isNotEmpty()){ "Path is required, did you call initialize before connect" }
    require(username.isNotEmpty()){ "Username is required, did you call initialize before connect" }
    if (!isConnected) {
        client.wss("wss://$baseurl/$path/$username") {
            println("Connecting")
            coroutineScope {
                serializableChannel.consumeAsFlow()
                    .map { sendSerialized(it) }
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
        println("Couldn't connect")
    }
}.retryWithBackoff()

internal suspend fun send(coordinates: Coordinates) {
    serializableChannel.send(coordinates)
}



