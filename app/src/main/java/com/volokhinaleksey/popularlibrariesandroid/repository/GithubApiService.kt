package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for interacting with the Github API
 */

interface GithubApiService {

    /**
     * Method for getting a list of users from the Github site
     */

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    /**
     * Method for getting a list of user's repositories from the Github site
     */

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Single<List<GithubUserRepo>>

    /**
     * A method for obtaining information about a specific user by his login on the Github site
     */

    @GET("users/{user}")
    fun getUserByLogin(@Path("user") user: String): Single<GithubUser>
}