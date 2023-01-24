pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Cherrio-LLC/RealTime")

            credentials{
                username = "Android"
                password = "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"
            }
        }
    }
    versionCatalogs {
        create("libs"){
            version("ktor","2.1.2")
            version("coroutine","1.6.4")

            library("client-core","io.ktor","ktor-client-core").versionRef("ktor")
            library("websocket", "io.ktor","ktor-client-websockets").versionRef("ktor")
            library("client-android","io.ktor","ktor-client-cio").versionRef("ktor")
            library("client-ios","io.ktor","ktor-client-darwin").versionRef("ktor")

            library("client-content-neg","io.ktor","ktor-client-content-negotiation").versionRef("ktor")
            library("client-json","io.ktor","ktor-serialization-kotlinx-json").versionRef("ktor")

            library("client-test","io.ktor","ktor-client-mock").versionRef("ktor")
            library("coroutine-core","org.jetbrains.kotlinx","kotlinx-coroutines-core").versionRef("coroutine")
            library("coroutine-test","org.jetbrains.kotlinx","kotlinx-coroutines-test").versionRef("coroutine")

            library("kstore","io.github.xxfast:kstore:0.1.1")
            library("koin","io.insert-koin:koin-core:3.2.2")
            library("orbit","org.orbit-mvi:orbit-core:4.4.0")
            library("android-viewmodel","androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
            library("orbit-compose","org.orbit-mvi:orbit-compose:4.4.0")

        }
    }
}

rootProject.name = "RealTime"
include(":androidApp")
include(":realtime")