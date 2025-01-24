plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dragger.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.mahfuz.retrofit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mahfuz.retrofit"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dragger Hilt
    implementation(libs.bundles.dragger.hilt) // Automatically includes runtime
    kapt(libs.dragger.hilt.compiler) // Compiler needs explicit kapt reference
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.2.0") // please check
    // android ktx
    implementation("androidx.activity:activity-ktx:1.10.0")
    // Room
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    // Viewmodel & LiveData
    implementation(libs.bundles.lifecycle)

    // Retrofit
    implementation(libs.bundles.retrofit.full)

    //kotlin Coroutines
    implementation (libs.bundles.coroutines)
}