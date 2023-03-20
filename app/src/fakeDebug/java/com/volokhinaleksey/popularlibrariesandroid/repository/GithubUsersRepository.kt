package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Interface for getting data from a remote repository
 */

interface GithubUsersRepository {

    /**
     * Method for getting a list of users
     *
     * @return The method returns a single RxJava object, in which the list of users is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    fun getUsers(): Single<List<GithubUserDTO>>

}

/**
 * Implementation of the interface for getting data from a remote repository
 */

class GithubUsersRepositoryImpl @Inject constructor() : GithubUsersRepository {

    /**
     * Method for getting a list of users
     *
     * @return The method returns a single RxJava object, in which the list of users is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    override fun getUsers(): Single<List<GithubUserDTO>> =
        Single.just<List<GithubUserDTO>>(listOf()).subscribeOn(Schedulers.io())
}