package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interface for interacting with the Github API
 */

interface GithubApiService {

    /**
     * Method for getting a list of users from the Github site
     */

    @GET("/users")
    fun getUsersData(): Single<List<GithubUserDTO>>

    /**
     * Method for getting a list of user's repositories from the Github site
     */

    @GET
    fun getUserRepos(@Url reposUrl: String): Single<List<GithubRepositoryDTO>>

    /**
     * A method for obtaining information about a specific user by his login on the Github site
     */

    @GET
    fun getUserData(@Url userUrl: String): Single<GithubUserDTO>

    @GET
    fun getUserReposCommits(@Url commitsUrl: String): Single<List<GithubCommitsDTO>>
}