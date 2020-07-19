plugins {
    id(Libs.Plugins.javaLibrary)
    id(Libs.Plugins.kotlinLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    sourceSets {
        map { it.java.srcDirs("src/${it.name}/kotlin")}
    }
}

dependencies {
    implementation(Libs.AndroidX.annotation)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)
}

