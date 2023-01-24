package com.dilivva.realtime

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json



internal var isConnected = false
class Data(engine: HttpClientEngine): DataApi{
    private val client = HttpClient(engine) {
        install(this)
    }
    private val coordinatesChannel = Channel<Coordinates>()
    private var baseurl = ""
    private var path = ""
    private var username = ""

    fun configure(_baseurl: String, _username: String, _path: String = "connect"){
        baseurl = _baseurl
        username = _username
        path = _path
    }
    override suspend fun send(coordinates: Coordinates) {
        coordinatesChannel.send(coordinates)
    }
     override fun connectToServer() = flow {
        checkConditions()
        if (!isConnected) {
            client.wss("wss://$baseurl/$path/$username") {
                emit("Connecting")
                coroutineScope {
                    getChannel()
                        .map { sendSerialized(it) }
                        .launchIn(this)
                    while (true) {
                        emit("Connected")
                        isConnected = true
                        val frame = incoming.receive()
                        if (frame is Frame.Text) {
                            emit(frame.readText())
                        }
                    }
                }
            }
        }else{
            emit("Couldn't connect")
        }
    }.retryWithBackoff()

    override fun getChannel() = coordinatesChannel.consumeAsFlow()
    private fun checkConditions(){
        when{
            baseurl.isEmpty() -> throw IllegalStateException("BaseUrl is required, did you call initialize before connect")
            path.isEmpty() -> throw IllegalStateException("Path is required, did you call initialize before connect")
            username.isEmpty() -> throw IllegalStateException("Username or Id is required, did you call initialize before connect")
        }
    }

    private fun install(httpClientConfig: HttpClientConfig<*>){
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




