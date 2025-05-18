plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias { libs.plugins.ktlint }
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tafreshiali.numera"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tafreshiali.numera"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("1.5.0")
            .editorConfigOverride(
                mapOf(
                    /* "indent_size" to "4",
                     "continuation_indent_size" to "4",*/
                    "remove_unused_imports" to "true",
                    "ktlint_standard_annotation" to "disabled",
                    "max_line_length" to "100",
                    "ktlint_standard_no-wildcard-imports" to "disabled",
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "ktlint_standard_package-name" to "disabled"
                )
            )
    }
}

tasks.register<Copy>("copyPreCommitHook") {
    description = "Copy pre-commit hook from the scripts folder into .git/hooks directory"
    group = "git hooks"
    //Always Copy the pre-commit file into .git/hooks directory to prevent issues
    outputs.upToDateWhen { false }
    from("$rootDir/git-hooks/pre-commit")
    into("$rootDir/.git/hooks/")
}
tasks.preBuild {
    dependsOn("copyPreCommitHook")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.compose.constraint.layout)
    implementation(libs.androidx.compose.viewmodel.lifecycle)
    implementation(libs.androidx.compose.runtime.lifecycle)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //google truth
    testImplementation(libs.google.truth)

    //splash screen
    implementation(libs.androidx.core.splashscreen)

    //dagger hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    //proto data store
    implementation(libs.proto.data.store.androidx)
    implementation(projects.core.protoDataStore)
}