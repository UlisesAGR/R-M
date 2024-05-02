plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rm.mobile.utils"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            // Optimize Code
            isMinifyEnabled = true
            // Optimize Includes
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        buildConfig = true
    }
}

dependencies {
    //core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.org.coroutines)
    //libraries
    implementation(libs.material)
    implementation(libs.bundles.com.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.glide)
    implementation(libs.konfetti)
    //test
    testImplementation(libs.junit)
    testImplementation(libs.org.coroutines.test)
    testImplementation(libs.bundles.test.mockito)
    //androidTest
    androidTestImplementation(libs.androidx.junit)
}
