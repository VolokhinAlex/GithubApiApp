import org.gradle.api.JavaVersion

object Versions {

    // Dagger 2
    const val dagger = "2.44"
    const val daggerAndroid = "2.42"
    const val daggerCompiler = "2.42"

    // Room
    const val roomRuntime = "2.5.0"
    const val roomCompiler = "2.5.0"
    const val roomRxJava = "2.5.0"

    // Timber
    const val timber = "5.0.1"

    // Coil
    const val coil = "2.2.2"

    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val retrofitRxAdapter = "2.9.0"
    const val okHttp = "4.10.0"
    const val okHttpLogging = "4.9.3"

    // RxJava
    const val rxJavaAndroid = "3.0.2"
    const val rxJava = "3.1.5"

    // Moxy
    const val moxy = "2.2.2"
    const val moxyCompiler = "2.2.2"
    const val moxyKtx = "2.2.2"
    const val moxyAndroidx = "2.2.2"

    // Cicerone
    const val cicerone = "6.6"

    // Android
    const val ktx = "1.9.0"

    // Design
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val constraintLayout = "2.1.4"

    // Tests
    const val truth = "1.1.3"
    const val mockitoCore = "5.2.0"
    const val mockitoInline = "5.2.0"
    const val mockitoKotlin = "4.1.0"
    const val junit = "4.13.2"
    const val fragmentTesting = "1.5.5"
    const val testCore = "1.5.0"
    const val testRunner = "1.5.2"
    const val extJunit = "1.1.5"
    const val extTruth = "1.5.0"
    const val espressoCore = "3.5.1"
    const val espressoIntents = "3.5.1"

    // Robolectric tests
    const val robolectric = "4.9"
}

object Config {
    const val applicationId = "com.volokhinaleksey.popularlibrariesandroid"
    const val minSdk = 27
    const val targetSdk = 33
    const val compileSdk = 33
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerAndroid}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerCompiler}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomRxJava = "androidx.room:room-rxjava3:${Versions.roomRxJava}"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val retrofitRxAdapter =
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitRxAdapter}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val loggingOkHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
}

object RxJava {
    const val rxJavaAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxJavaAndroid}"
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
}

object Moxy {
    const val moxy = "com.github.moxy-community:moxy:${Versions.moxy}"
    const val moxyCompiler = "com.github.moxy-community:moxy-compiler:${Versions.moxyCompiler}"
    const val moxyKtx = "com.github.moxy-community:moxy-ktx:${Versions.moxyKtx}"
    const val moxyAndroidx = "com.github.moxy-community:moxy-androidx:${Versions.moxyAndroidx}"
}

object Cicerone {
    const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
}

object Android {
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Tests {
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoInline =  "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val extTruth = "androidx.test.ext:truth:${Versions.extTruth}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}

