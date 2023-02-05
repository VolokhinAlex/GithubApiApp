package com.volokhinaleksey.popularlibrariesandroid.repository

import javax.inject.Inject

/**
 * Interface for working with retrofit
 */

interface ApiHolder {
    val apiService: GithubApiService
}

/**
 * Implementation of the interface for working with retrofit.
 * The retrofit object itself is created automatically using dependency injection.
 */

class GithubApiHolder @Inject constructor(override val apiService: GithubApiService) : ApiHolder