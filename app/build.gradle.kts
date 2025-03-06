plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "edu.badpals.examenfinalpdmm"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.badpals.examenfinalpdmm"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.zxing.android.embedded)
    implementation(libs.core)
    implementation(libs.security.crypto)
    implementation(libs.preference)
    implementation(libs.sqliteassethelper)
    implementation(libs.room.common)
    implementation(libs.security.app.authenticator)
}