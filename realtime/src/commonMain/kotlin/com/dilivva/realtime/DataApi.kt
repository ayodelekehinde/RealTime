package com.dilivva.realtime

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

internal interface DataApi {
    suspend fun send(coordinates: Coordinates)
    fun connectToServer(): Flow<String>
    fun getChannel(): Flow<Coordinates>
}