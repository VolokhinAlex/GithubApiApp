package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser

interface GithubUsersRepo {
    fun getUsers(): List<GithubUser>
}

class GithubUsersRepoImpl : GithubUsersRepo {

    override fun getUsers(): List<GithubUser> = listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        )

}