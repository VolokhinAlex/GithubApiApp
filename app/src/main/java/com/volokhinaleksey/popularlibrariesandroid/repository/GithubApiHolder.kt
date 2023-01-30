package com.volokhinaleksey.popularlibrariesandroid.repository

import javax.inject.Inject

interface ApiHolder {
    val apiService: GithubApiService
}

class GithubApiHolder @Inject constructor(override val apiService: GithubApiService) : ApiHolder