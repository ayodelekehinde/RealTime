package com.dilivva.realtime

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json

class Data(engine: HttpClientEngine) : DataApi {
    private val client = HttpClient(engine) {
        install(this)
    }
    private val coordinatesChannel = Channel<Coordinates>()
    private var baseurl = ""
    private var path = ""
    private var username = ""

    fun configure(_baseurl: String, _username: String, _path: String = "connect") {
        baseurl = _baseurl
        username = _username
        path = _path
    }

    override suspend fun send(coordinates: Coordinates) {
        coordinatesChannel.trySend(coordinates)
    }

    override fun connectToServer() = flow<String> {
        checkConditions()
        client.wss("wss://$baseurl/$path/$username") {
            coroutineScope {
                getChannel().map { sendSerialized(it) }.launchIn(this)
                while (true) {
                    when (val frame = incoming.receive()) {
                        is Frame.Text -> {
                            val message = frame.readText()
                            if (message == "Unauthenticated") throw UnauthenticatedException
                            emit(message)
                        }
                        else -> {}
                    }
                }
            }
        }
    }.retryWithBackoff()

    override fun getChannel() = coordinatesChannel.receiveAsFlow()
    private fun checkConditions() {
        when {
            baseurl.isEmpty() -> throw IllegalStateException("BaseUrl is required, did you call initialize before connect")
            path.isEmpty() -> throw IllegalStateException("Path is required, did you call initialize before connect")
            username.isEmpty() -> throw IllegalStateException("Username or Id is required, did you call initialize before connect")
        }
    }

    private fun install(httpClientConfig: HttpClientConfig<*>) {
        httpClientConfig.install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
}

@kotlinx.serialization.Serializable
data class Coordinates(
    val lat: String,
    val long: String
)




