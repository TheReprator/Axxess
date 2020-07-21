plugins {
    id(Libs.Plugins.androidApplication)
    kotlin(Libs.Plugins.kotlinAndroid)
    kotlin(Libs.Plugins.kotlinAndroidExtensions)
    kotlin(Libs.Plugins.kotlinKapt)
    id(Libs.Plugins.kotlinNavigation)
    id(Libs.Plugins.kaptDagger)
}

androidExtensions {
    isExperimental = true
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    javacOptions {
        option("-Xmaxerrs", 500)
    }
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = AppConstant.applicationPackage

        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        versionCode = AppVersion.versionCode
        versionName = AppVersion.versionName

        testInstrumentationRunner = Libs.TestDependencies.testRunner

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        resConfigs(AndroidSdk.locales)

        buildConfigField("String", AppConstant.hostConstant, "\"${AppConstant.host}\"")
    }

    flavorDimensions(AppConstant.flavourDimension)

    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

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
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(project(AppModules.moduleBase))
    implementation(project(AppModules.moduleBaseAndroid))
    implementation(project(AppModules.moduleNavigation))
    implementation(project(AppModules.moduleSearch))
    implementation(project(AppModules.moduleItemDetail))

    implementation(Libs.AndroidX.appcompat)

    implementation(Libs.Google.materialWidget)
    implementation(Libs.AndroidX.annotation)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintlayout)

    kapt(Libs.AndroidX.Lifecycle.compiler)
    implementation(Libs.AndroidX.Lifecycle.extensions)

    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)

    implementation(Libs.AndroidX.multidex)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.jacksonConverter)
    implementation(Libs.Retrofit.jacksonKotlinModule)

    implementation(Libs.OkHttp.loggingInterceptor)

    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.DaggerHilt.hilt)
    kapt(Libs.DaggerHilt.compiler)

    implementation(Libs.DaggerHilt.viewModel)
    kapt(Libs.DaggerHilt.androidXCompiler)

    testImplementation(Libs.TestDependencies.Mockk.unitTest)
    testImplementation(Libs.TestDependencies.truth)

    testImplementation(Libs.DaggerHilt.instrumentation)
    androidTestImplementation(Libs.DaggerHilt.instrumentation)
    kaptAndroidTest(Libs.DaggerHilt.compiler)
    kaptAndroidTest(Libs.DaggerHilt.androidXCompiler)
}


