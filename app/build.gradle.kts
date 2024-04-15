plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
   alias(libs.plugins.orgJetbrainsKotlinkapt)
   alias(libs.plugins.daggerHiltAndroid)
}

android {
    namespace = "com.example.apimvvmunitdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apimvvmunitdemo"
        minSdk = 24
        targetSdk = 34
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

        debug {
            buildConfigField("String", "API_KEY", "\"b4422603efd7455493100b62d62d9141\"")
            //FIXME remove the string and add your api key
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
        buildConfig  = true
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

    //Architectural Components
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidxlifecycleviewmodelktx)
    implementation(libs.androidxlifecycleextensions)

    implementation(libs.androidxHiltNavigationCompose)
    implementation(libs.androidxNavigationCompose)
    implementation(libs.androidxLifecycleRuntimeCompose)

    implementation(libs.androidxRecyclerview)
    implementation(libs.bumtechglide)
    //Retrofit
    implementation(libs.squareupRetrofit)
    implementation(libs.squareupRetrofitgson)

    implementation(libs.coinCompose)
    implementation(libs.androidbrowser)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //Paging
    implementation(libs.androidx.paging.common.ktx)
    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kotlinxcoroutinestest)
    testImplementation(libs.kotlinxcoroutinescore)
    testImplementation(libs.coretesting)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Hilt
    implementation(libs.androidx.hilt.work)
    implementation(libs.workruntime)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
}