package com.dilivva.realtime.android

import android.app.Application
import com.dilivva.realtime.Realtime

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Realtime.configureApp("realtime.dilivva.com","Android")
    }
}