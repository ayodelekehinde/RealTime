package com.dilivva.realtime.android

import android.app.Application
import com.dilivva.realtime.Realtime

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Realtime.configureApp("realtime.dilivva.com.ng","acaf50a0-9cbb-11ed-816d-d9e20e6886fe")
    }
}