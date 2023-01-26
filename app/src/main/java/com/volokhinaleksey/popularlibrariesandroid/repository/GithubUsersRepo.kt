package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepos(user: String): Single<List<GithubUserRepo>>

    fun getUserByLogin(user: String): Single<GithubUser>
}

class GithubUsersRepoImpl(private val remoteApiSource: GithubApiService) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        remoteApiSource.getUsers().subscribeOn(Schedulers.io())

    override fun getUserRepos(user: String): Single<List<GithubUserRepo>> =
        remoteApiSource.getUserRepos(user = user).subscribeOn(Schedulers.io())

    override fun getUserByLogin(user: String): Single<GithubUser> =
        remoteApiSource.getUserByLogin(user).subscribeOn(Schedulers.io())
}