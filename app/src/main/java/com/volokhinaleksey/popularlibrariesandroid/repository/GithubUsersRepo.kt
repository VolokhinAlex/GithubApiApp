package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Interface for getting data from a remote repository
 */

interface GithubUsersRepo {
    /**
     * Method for getting a list of users
     */

    fun getUsers(): Single<List<GithubUser>>

    /**
     * Method for getting a list of user's repositories
     * @param user - User login
     */

    fun getUserRepos(user: String): Single<List<GithubUserRepo>>

    /**
     * Method for getting user information
     * @param user - User login
     */

    fun getUserByLogin(user: String): Single<GithubUser>
}

/**
 * Implementation of the interface for getting data from a remote repository
 */

class GithubUsersRepoImpl(private val remoteApiSource: GithubApiService) : GithubUsersRepo {

    /**
     * Method for getting a list of users
     */

    override fun getUsers(): Single<List<GithubUser>> =
        remoteApiSource.getUsers().subscribeOn(Schedulers.io())

    /**
     * Method for getting a list of user's repositories
     * @param user - User login
     */

    override fun getUserRepos(user: String): Single<List<GithubUserRepo>> =
        remoteApiSource.getUserRepos(user = user).subscribeOn(Schedulers.io())

    /**
     * Method for getting user information
     * @param user - User login
     */

    override fun getUserByLogin(user: String): Single<GithubUser> =
        remoteApiSource.getUserByLogin(user).subscribeOn(Schedulers.io())
}