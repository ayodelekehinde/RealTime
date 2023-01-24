package com.dilivva.realtime

import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(DataApi::class)
class DataTest {

    private val mockEngine = MockEngine { _ ->
        respond(
            content = """{"status":"successful", "message":"success", "data":"Yes"}""",
            status = HttpStatusCode.SwitchingProtocols,
            headers = headersOf(HttpHeaders.ContentType, "application/text")
        )
    }
    private val data = Data(mockEngine)

    @Test
    fun connect_throwException_ForInitialization() = runTest {
        assertFailsWith<IllegalStateException> {
            data.connectToServer().collectLatest {
                println(it)
            }
        }
    }

    @Test
    fun connect_success_ForConnection() = runTest {
        val mocker = Mocker()
        val data = MockDataApi(mocker)
        mocker.every { data.connectToServer() } returns flow { emit("Connected") }
        val connectResponse = data.connectToServer().unBox()
        assertEquals("Connected", connectResponse)
    }

    @Test
    fun send_Coordinates_Emits() = runTest {
        val mocker = Mocker()
        val data = MockDataApi(mocker)
        mocker.every { data.getChannel() } returns flow { emit(Coordinates(lat = "lat", long = "long")) }
        mocker.everySuspending { data.send(Coordinates(lat = "lat", long = "long")) } returns Unit
        data.send(Coordinates(lat = "lat", long = "long"))
        val connectResponse = data.getChannel().unBox()
        assertEquals(Coordinates(lat = "lat", long = "long"), connectResponse)
    }
}