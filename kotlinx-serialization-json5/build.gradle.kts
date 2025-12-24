plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.serialization)
}

version = "0.3.0"

kotlin {
    explicitApi()

    androidLibrary {
        namespace = "com.youfeng.kotlinx.serialization.json5"
        compileSdk = 36
        minSdk = 24
        
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    // JVM 平台
    jvm {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    // JavaScript 平台
    js(IR) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        nodejs()
    }

    // WebAssembly
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)  // 新注解
    wasmJs {
        browser()
        nodejs()
    }

    // Native 平台 - iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Native 平台 - macOS
    macosX64()
    macosArm64()

    // Native 平台 - watchOS
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    watchosDeviceArm64()

    // Native 平台 - tvOS
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    // Native 平台 - Linux
    linuxX64()
    linuxArm64()

    // Native 平台 - Windows
    mingwX64()

    // Native 平台 - Android Native
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    sourceSets {
        // ===== Common =====
        commonMain {
            dependencies {
                api(libs.kotlinx.serialization.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        // ===== JVM =====
        jvmTest {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }

        // ===== JS =====
        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
