package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubApiService
import com.volokhinaleksey.popularlibrariesandroid.utils.AndroidNetworkStatus
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("basedUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun client(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun api(
        @Named("basedUrl") baseUrl: String,
        gson: Gson,
        client: OkHttpClient
    ): GithubApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(GithubApiService::class.java)

    @Singleton
    @Provides
    fun networkStatus(app: App): NetworkStatus = AndroidNetworkStatus(app)
}