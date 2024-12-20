plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.pojetopdm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pojetopdm"
        minSdk = 24
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

    buildFeatures{
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    implementation (project(":camera_module"))
    implementation ("com.google.ar:core:1.46.0")

    implementation ("de.javagl:obj:0.4.0")

    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("com.google.android.material:material:1.1.0")
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-auth:22.1.0")
    implementation ("com.google.firebase:firebase-firestore-ktx")
}