plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "ru.zhogin.app.done"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation)
    implementation(libs.javax.inject)
    implementation(libs.androidx.lifecycle.viewmodule.ktx)
    implementation(libs.androidx.lifecycle.viewmodule.compose)
    kapt(libs.dagger.hilt.compiler)
    ksp(libs.androidx.room.compiler)
    api(libs.androidx.room.ktx)
    implementation(project(":app-uikit"))
    implementation(project(":app-tasks"))
    implementation(project(":app-settings"))
}