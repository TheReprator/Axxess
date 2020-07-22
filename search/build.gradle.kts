plugins {
    id(Libs.Plugins.androidLibrary)
    kotlin(Libs.Plugins.kotlinAndroid)
    kotlin(Libs.Plugins.kotlinAndroidExtensions)
    kotlin(Libs.Plugins.kotlinKapt)
    id(Libs.Plugins.kaptDagger)
    id(Libs.Plugins.kotlinNavigation)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)

        testInstrumentationRunner = Libs.TestDependencies.testRunner

        consumerProguardFiles(
            file("proguard-rules.pro")
        )

        resConfigs(AndroidSdk.locales)
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        map { it.java.srcDirs("src/${it.name}/kotlin") }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
        }
    }

    packagingOptions {
        pickFirst ("META-INF/*")
    }
}

androidExtensions {
    isExperimental = true
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    generateStubs = true
    javacOptions {
        option("-Xmaxerrs", 500)
    }
}

dependencies {
    implementation(project(AppModules.moduleNavigation))
    implementation(project(AppModules.moduleBase))
    implementation(project(AppModules.moduleBaseAndroid))

    implementation(Libs.AndroidX.annotation)
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.AndroidX.cardView)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintlayout)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.coroutineTest)

    implementation(Libs.AndroidX.Lifecycle.livedata)

    implementation(Libs.AndroidX.Navigation.fragmentKtx)

    implementation(Libs.AndroidX.Fragment.fragment)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.jacksonConverter)

    implementation(Libs.DaggerHilt.hilt)
    kapt(Libs.DaggerHilt.compiler)

    implementation(Libs.DaggerHilt.viewModel)
    kapt(Libs.DaggerHilt.androidXCompiler)

    testImplementation(Libs.DaggerHilt.instrumentation)
    androidTestImplementation(Libs.DaggerHilt.instrumentation)
    kaptAndroidTest(Libs.DaggerHilt.compiler)
    kaptAndroidTest(Libs.DaggerHilt.androidXCompiler)

    testImplementation(Libs.TestDependencies.Mockk.unitTest)
    testImplementation(Libs.TestDependencies.truth)
    testImplementation(Libs.TestDependencies.archCore)

    testImplementation(Libs.TestDependencies.junit)
}
