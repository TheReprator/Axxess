object AndroidSdk {
    const val min = 15
    const val compile = 30
    const val target = compile

    val locales = listOf("en", "hi")
}

object AppConstant {
    const val applicationPackage = "reprator.axxess"
    const val name = "Axxess"
    const val host = "https://api.imgur.com/3/"
    const val hostConstant = "HOST"
    const val flavourDimension = "type"
}

object AppVersion {
    const val versionCode = 1
    const val versionName = "1.0"
}

object AppModules {
    const val moduleApp = ":app"
    const val moduleBase = ":base"
    const val moduleBaseAndroid = ":base-android"
    const val moduleNavigation = ":navigation"
    const val moduleSearch = ":search"
    const val moduleItemDetail = ":itemDetail"
}

object Libs {

    object Plugins {
        const val androidApplication = "com.android.application"
        const val crashlytics = "com.google.firebase.crashlytics"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
        const val kotlinLibrary = "kotlin"
        const val kotlinAndroid = "android"
        const val kotlinAndroidExtensions = "android.extensions"
        const val kotlinKapt = "kapt"
        const val kaptDagger = "dagger.hilt.android.plugin"
        const val kotlinNavigation = "androidx.navigation.safeargs.kotlin"
        const val realm = "realm-android"
        const val classPathJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.6.0.0"
        const val pluginJunit5 = "de.mannodermaus.android-junit5"
    }

    const val inject = "javax.inject:javax.inject:1"
    const val realm = "io.realm:realm-gradle-plugin:6.0.1"
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.1"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val facebook = "com.facebook.android:facebook-login:6.1.0"
    const val countryPicker = "com.hbb20:ccp:2.3.8"
    const val powerSpinner = "com.github.skydoves:powerspinner:1.0.8"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.1.1"
    const val shimmerRecyclerView = "com.github.sharish:ShimmerRecyclerView:v1.3"
    const val scanner = "com.budiyev.android:code-scanner:2.1.0"
    const val dotIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
    const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"

    object Coil {
        private const val version = "0.11.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object EPoxy {
        private const val version = "3.9.0"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
        const val paging = "com.airbnb.android:epoxy-paging:$version"
        const val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
    }

    object Google {
        const val materialWidget = "com.google.android.material:material:1.3.0-alpha01"
        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        const val gmsGoogleServices = "com.google.gms:google-services:4.3.3"
    }

    object Firebase {
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.4.3"
        const val core = "com.google.firebase:firebase-core:17.2.2"
        const val iid = "com.google.firebase:firebase-iid:20.2.0"
        const val messaging = "com.google.firebase:firebase-messaging:20.2.0"
        const val authFirebase = "com.google.firebase:firebase-auth:19.3.1"
        const val authPlayServices = "com.google.android.gms:play-services-auth:18.0.0"
        const val authPlayPhoneServices =
            "com.google.android.gms:play-services-auth-api-phone:17.4.0"
        const val authUI = "com.firebaseui:firebase-ui-auth:4.3.1"
        const val remoteConfig = "com.google.firebase:firebase-config:19.1.1"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.0.1"
        const val playServiceCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.5"
    }

    object Kotlin {
        private const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.3.8"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha04"
        const val cardView = "androidx.cardview:cardview:1.0.0"
        const val annotation = "androidx.annotation:annotation:1.2.0-alpha01"
        const val multidex = "androidx.multidex:multidex:2.0.1"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta8"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha01"
        const val viewPager2 = "androidx.viewpager2:viewpager2:1.1.0-alpha01"

        const val browser = "androidx.browser:browser:1.3.0-alpha01"

        object Fragment {
            private const val version = "1.3.0-alpha06"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0-alpha05"
            const val commonJava = "androidx.lifecycle:lifecycle-common-java8:$version"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }

        object SavedState {
            const val savedState = "androidx.savedstate:savedstate:1.1.0-alpha01"
            const val savedState_viewModal =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.0-alpha05"

            const val savedState_bundle = "androidx.savedstate:savedstate-bundle:1.0.0-alpha01"
        }

        object Navigation {
            private const val version = "2.3.0"
            const val runtime = "androidx.navigation:navigation-runtime:$version"
            const val runtimeKtx = "androidx.navigation:navigation-runtime-ktx:$version"
            const val fragment = "androidx.navigation:navigation-fragment:$version"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.3.0-alpha01"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Work {
            private const val version = "2.3.4"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }

        object Paging {
            private const val version = "2.1.0"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val jacksonConverter = "com.squareup.retrofit2:converter-jackson:$version"
        const val jacksonKotlinModule = "com.fasterxml.jackson.module:jackson-module-kotlin:2.10.2"
        const val gson = "com.squareup.retrofit2:converter-gson:2.3.0"
    }

    object OkHttp {
        private const val version = "4.8.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Dagger {
        private const val version = "2.28.2"
        const val runtime = "com.google.dagger:dagger:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val android_support = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val android_support_compiler = "com.google.dagger:dagger-android-processor:$version"
        const val javaxInject = "javax.inject:javax.inject:1"
    }

    object DaggerHilt {
        private const val version = "2.28-alpha"
        private const val androidXversion = "1.0.0-alpha01"

        const val classPath = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val androidXCompiler = "androidx.hilt:hilt-compiler:$androidXversion"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$androidXversion"
        const val instrumentation = "com.google.dagger:hilt-android-testing:$version"
    }

    object SavedDaggerAssist {
        private const val version = "0.5.2"
        const val daggerRuntime = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val daggerCompiler = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    object TestDependencies {
        object Mockk {
            private const val version = "1.10.0"
            const val unitTest = "io.mockk:mockk:$version"
            const val instrumentedTest = "io.mockk:mockk-android:$version"
        }

        const val assertj = "org.assertj:assertj-core:3.13.2"
        const val truth = "com.google.truth:truth:1.0.1"
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:5.5.2"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:5.5.2"
        const val junit = "junit:junit:4.12"
        const val junitInstrumented = "androidx.test.ext:junit:1.1.1"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"

    }
}