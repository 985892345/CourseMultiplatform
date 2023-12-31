import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.ktProvider)
}

kotlin {
  // 目前 wasmJs 还缺少第三方库，暂时注释
//  @OptIn(ExperimentalWasmDsl::class)
//  wasmJs {
//    moduleName = "composeApp"
//    browser {
//      commonWebpackConfig {
//        outputFileName = "composeApp.js"
//      }
//    }
//    binaries.executable()
//  }

  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    val desktopMain by getting

    androidMain.dependencies {
      implementation(libs.compose.ui.tooling.preview)
      implementation(libs.androidx.activity.compose)
      implementation(libs.androidWheel.extensions.android)
      implementation(libs.ktor.engines.okhttp)
    }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material)
      implementation(compose.ui)
      @OptIn(ExperimentalComposeLibrary::class)
      implementation(compose.components.resources)
      implementation(libs.kotlinx.datetime)
      implementation(libs.kotlinx.serialization)
      implementation(libs.ktor.content.negotiation)
      implementation(libs.ktor.json)
      implementation(libs.ktor.fit.annotation)
      implementation(ktProvider.manager)
    }
    desktopMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.ktor.engines.okhttp)
    }
    iosMain.dependencies {
      implementation(libs.ktor.engines.darwin)
    }
  }
}

dependencies {
  val ktorfitKsp = libs.ktor.fit.ksp
  add("kspAndroid", ktorfitKsp)
  add("kspDesktop", ktorfitKsp)
  add("kspIosX64", ktorfitKsp)
  add("kspIosArm64", ktorfitKsp)
  add("kspIosSimulatorArm64", ktorfitKsp)

  add("kspCommonMainMetadata", ktProvider.ksp)
  add("kspAndroid", ktProvider.ksp)
  add("kspDesktop", ktProvider.ksp)
  add("kspIosX64", ktProvider.ksp)
  add("kspIosArm64", ktProvider.ksp)
  add("kspIosSimulatorArm64", ktProvider.ksp)
}

android {
  namespace = "com.multiplatform.course"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = "com.multiplatform.course"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0.0"
  }
  buildTypes {
    release {
      isMinifyEnabled = true
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  dependencies {
    debugImplementation(libs.compose.ui.tooling)
  }
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "com.multiplatform.course"
      packageVersion = "1.0.0"
    }

    buildTypes {
      release {
        proguard {
          isEnabled.set(true)
          configurationFiles.from(projectDir.resolve("proguard-rules.pro"))
        }
      }
    }
  }
}
//
//compose.experimental {
//  web.application {}
//}