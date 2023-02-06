plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    `maven-publish`
    id("co.touchlab.faktory.kmmbridge")
    id("org.kodein.mock.mockmp")
}
group = "com.dilivva"

mockmp {
    usesHelper = true
}
kotlin {
    android {
        publishAllLibraryVariants()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Library that enables smooth consumption of Dilivva websocket server"
        homepage = "https://github.com/Cherrio-LLC/RealTime"
        version = "1.0"
        ios.deploymentTarget = "13"
        podfile = project.file("../DilivvaTest/Podfile")
        framework {
            baseName = "RealTime"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.websocket)
                implementation(libs.coroutine.core)
                implementation(libs.client.core)
                implementation(libs.client.content.neg)
                implementation(libs.client.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.client.test)
                implementation(libs.coroutine.test)
            }
        }
        val androidMain by getting{
            dependencies {
                implementation(libs.client.android)
            }
        }
        val androidTest by getting {
            dependsOn(commonTest)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.client.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
addGithubPackagesRepository()
kmmbridge{
    mavenPublishArtifacts()
    githubReleaseVersions()
    spm()
    cocoapods("git@github.com:Cherrio-LLC/RealTimePodspec.git")
    versionPrefix.set("0.1")
}

android {
    namespace = "com.dilivva.realtime"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest> {
    deviceId = "iPhone 14"
}