// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion = "1.7.21"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply false
}

dependencies {
   // detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}
