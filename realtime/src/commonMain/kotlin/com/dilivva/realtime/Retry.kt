package com.dilivva.realtime

import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import kotlin.time.Duration.Companion.seconds

/**
 *  Retries a `Flow<T>` indefinitely with exponential backoff
 *
 *  @param delay -- delay in seconds between retries
 *  @param delayAtMost -- maximum delay in seconds after which this value is used to delay
 */

fun  Flow<String>.retryWithBackoff(
    delay: Int = 1,
    delayAtMost: Int = 10,
): Flow<String> {
    var mDelay = delay

    return retryWhen { cause, _ ->
        emit(cause.getErrorMessage())
        val shouldRetry = shouldRetryBasedOnException(cause)
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

private fun Throwable.getErrorMessage(): String{
    return when{
        this.isNetworkError()-> "Network error"
        this is ClosedReceiveChannelException -> message ?: "Connection closed"
        else -> message ?: "Error occurred, try again."
    }
}