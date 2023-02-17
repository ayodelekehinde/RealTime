package com.dilivva.realtime.android

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewModelScope
import com.dilivva.realtime.Coordinates
import com.dilivva.realtime.Realtime
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class ViewModel: androidx.lifecycle.ViewModel() {

    init {
        getLocation()
    }

    fun startConnection()= viewModelScope.launch{
        println("CoNNECTED=${Realtime.isConnected()}")
        if (!Realtime.isConnected()) {
            Realtime.configureApp("realtime.dilivva.com.ng", "acaf50a0-9cbb-11ed-816d-d9e20e6886fe")
            Realtime.connect(this)
        }
    }

    private fun getLocation() = viewModelScope.launch {
            while (true) {
                val long = randomNumbers()
                val lat = randomNumbers()
                val coordinate = Coordinates(lat, long)
                Realtime.sendData(coordinate)
                delay(1000)
            }
    }

    private fun randomNumbers(): String{
        return generateSequence {
            // this lambda is the source of the sequence's values
            Random.nextInt(1..69)
        }
            // make the values distinct, so there's no repeated ints
            .distinct()
            // only fetch 6 values
            // Note: It's very important that the source lambda can provide
            //       this many distinct values! If not, the stream will
            //       hang, endlessly waiting for more unique values.
            .take(6)
            // sort the values
            .sorted()
            // and collect them into a Set
            .toSet()
            .joinToString("")
    }
}