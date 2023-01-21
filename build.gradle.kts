plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    id("co.touchlab.faktory.kmmbridge") version "0.3.4" apply false
    kotlin("plugin.serialization") version "1.7.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val lib_version: String by project
subprojects {
    version = co.touchlab.faktory.versionmanager.GitTagVersionManager.getVersion(project = project, versionPrefix = lib_version)
}