package com.dilivva.realtime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform