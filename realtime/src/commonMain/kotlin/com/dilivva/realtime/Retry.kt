package com.dilivva.realtime

import io.ktor.util.network.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds

/**
 *  Retries a `Flow<T>` indefinitely with exponential backoff
 *
 *  @param delay -- delay in seconds between retries
 *  @param delayAtMost -- maximum delay in seconds after which this value is used to delay
 */
fun <T> Flow<T>.retryWithBackoff(
    delay: Int = 1,
    delayAtMost: Int = 10,
): Flow<T> {
    var mDelay = delay

    return retryWhen { cause, _ ->
        println("ERROR=${cause::class}")
        val shouldRetry = shouldRetryBasedOnException(cause)
        isConnected = !shouldRetry
        println("Is retrying? $shouldRetry")

        if (shouldRetry) {
            delay(mDelay.seconds)
            val next = mDelay * 2
            mDelay = if (next < delayAtMost) next else delayAtMost
            true
        } else{
            false
        }
    }
}

private fun shouldRetryBasedOnException(cause: Throwable): Boolean{
    val listOfExceptions = getExceptions() + ClosedReceiveChannelException::class
    return listOfExceptions.any { cause::class == it }
}