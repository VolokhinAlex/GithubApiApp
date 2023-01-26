package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Single<List<GithubUserRepo>>

    @GET("users/{user}")
    fun getUserByLogin(@Path("user") user: String): Single<GithubUser>
}