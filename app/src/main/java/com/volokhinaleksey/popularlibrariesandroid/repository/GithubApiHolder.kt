package com.volokhinaleksey.popularlibrariesandroid.repository

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

interface ApiHolder {
    val apiService: GithubApiService
}

class GithubApiHolder @Inject constructor(override val apiService: GithubApiService) : ApiHolder

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}