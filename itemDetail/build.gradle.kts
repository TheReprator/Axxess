plugins {
    id(Libs.Plugins.androidLibrary)
    kotlin(Libs.Plugins.kotlinAndroid)
    kotlin(Libs.Plugins.kotlinAndroidExtensions)
    kotlin(Libs.Plugins.kotlinKapt)
    id(Libs.Plugins.kotlinNavigation)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
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

    packagingOptions.exclude("META-INF/main.kotlin_module")
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

    implementation(Libs.Google.materialWidget)
    implementation(Libs.AndroidX.annotation)
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.AndroidX.cardView)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintlayout)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)

    implementation(Libs.AndroidX.Lifecycle.livedata)

    implementation(Libs.AndroidX.Navigation.fragmentKtx)

    implementation(Libs.AndroidX.Fragment.fragment)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)

    implementation(Libs.DaggerHilt.hilt)
    kapt(Libs.DaggerHilt.compiler)

    testImplementation(Libs.TestDependencies.junit)
    testImplementation(Libs.TestDependencies.jupiterApi)
    testImplementation(Libs.TestDependencies.Mockk.unitTest)

    testImplementation(Libs.DaggerHilt.instrumentation)
    androidTestImplementation(Libs.DaggerHilt.instrumentation)
    kaptAndroidTest(Libs.DaggerHilt.compiler)
    kaptAndroidTest(Libs.DaggerHilt.androidXCompiler)
}
