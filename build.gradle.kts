plugins {
    kotlin("multiplatform") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.android.library")
    id("kotlin-android-extensions")
}

val coroutines_version = "1.5.2-native-mt"

group = "me.andrewreed"
version = "0.0.1"

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
}

kotlin {
    android()
    iosSimulatorArm64 {
        binaries.framework("library")
    }
    iosArm64("ios") {
        binaries.framework("library")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version") {
                    version {
                        strictly(coroutines_version)
                    }
                }
                implementation("io.insert-koin:koin-core:3.1.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("app.cash.turbine:turbine:0.7.0")
                // this is required because turbine has an issue finding the correct coroutines version on the android target.
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version") {
                    version { strictly(coroutines_version) }
                }
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.4.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        disable("UnsafeExperimentalUsageError", "UnsafeExperimentalUsageWarning")
    }
}
