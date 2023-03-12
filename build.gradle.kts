plugins {
    alias(libs.plugins.android.application)  apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.detekt)

}

dependencies {
    // detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}