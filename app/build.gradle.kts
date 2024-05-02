plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.navigationKotlin)
    alias(libs.plugins.devtoolsKsp)
}

android {
    namespace = "com.rm.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rm.mobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable = false
            // Optimize Code
            isMinifyEnabled = true
            // Optimize Resource
            isShrinkResources = true
            // Optimize Includes
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            resValue("string", "app_name", "R&M")
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "R&M Debug")
            buildConfigField("String", "DATABASE_NAME", "\"database\"")
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("String", "CHARACTER", "\"character\"")
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
        viewBinding = true
    }
}

dependencies {
    //modules
    implementation(project(":greenbox"))
    implementation(project(":utils"))
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
    implementation(libs.splashscreen)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.com.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.bundles.androidx.room)
    ksp(libs.room.compiler)
    implementation(libs.glide)
    ksp(libs.glide.compiler)
    implementation(libs.konfetti)
    implementation(libs.shimmer)
    implementation(libs.paging)
    //test
    testImplementation(libs.junit)
    testImplementation(libs.org.coroutines.test)
    testImplementation(libs.bundles.test.mockito)
    //androidTest
    androidTestImplementation(libs.androidx.junit)
}
