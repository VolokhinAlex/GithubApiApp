package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import io.reactivex.rxjava3.core.Observable

interface GithubUsersRepo {
    fun getUsers(): Observable<GithubUser>
}

class GithubUsersRepoImpl : GithubUsersRepo {
    override fun getUsers(): Observable<GithubUser> = Observable.fromIterable(
        listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        ))
}