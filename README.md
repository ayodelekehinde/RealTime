# <img src="https://kotlinlang.org/assets/images/favicon.svg" height="23"/> RealTime
[![Run tests](https://github.com/Cherrio-LLC/RealTime/actions/workflows/test.yml/badge.svg)](https://github.com/Cherrio-LLC/RealTime/actions/workflows/test.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.7.10-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)


![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![badge-mac](http://img.shields.io/badge/platform-macos-111111.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/platform-tvos-808080.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat)


A lightweight Kotlin multiplatform library that handles websocket connection.
Uses Ktor websocket under the hood.

## Features
- Connection
- Reconnection retrials
- Good error handling
- Clean APIs
- Fast and light
- Multiplatform (iOS/Android)

## Adding to your project

Realtime is published on Github packages

### Android
Add the maven url to your *build.gradle.kts*
```kotlin
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/Cherrio-LLC/RealTime")

        credentials{
            username = "Android"
            password = "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"
        }
    }
}
```

Include the dependency. Latest version ![GitHub release (latest by date)](https://img.shields.io/github/v/release/Cherrio-LLC/RealTime)
```kotlin
implementation("github.cherrio:realtime-android:<version>")
```

### iOS

You have to add the github token to your .netrc file on home directory like:

```
machine maven.pkg.github.com
  login iOS
  password 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

```

Add the source to your Podfile

```
source 'https://github.com/Cherrio-LLC/RealTimePodspec.git'
```
Then add
```
pod 'realtime', '~> 0.1.5'
```


## Usage
Configure realtime on app initialization

### Android
Add the initialization to the onCreate of the class extending *Application*
```kotlin
override fun onCreate() {
    super.onCreate()
    Realtime.configureApp("baseUrl","UserId")
}
```

### iOS
SwiftUi initialize in the *struct* extending *App* 
```swift
init(){
  RealtimeKt.configureApp(baseurl: "baseUrl", username: "iOS")      
}
```
UIKit is different, you have to initialize in the *AppDelegate* on *didFinishLaunchingWithOptions*

```swift
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        RealtimeKt.configureApp(baseurl: "baseUrl", username: "iOS")
        return true
}
```

## To connect
Check the example apps



### RoadMap
There's a lot of updates to come.
1. Send generic data
2. More tests
3. Improve docs

## License

    Copyright 2022 Ayodele Kehinde

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.