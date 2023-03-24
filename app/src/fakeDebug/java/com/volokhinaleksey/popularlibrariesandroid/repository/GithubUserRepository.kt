package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface GithubUserRepository {

    /**
     * Method for getting a list of user's repositories.
     * @param user - A class with user data
     * @exception IllegalStateException - The method throws an exception if the user does not have a link to the list of repositories
     * @return The method returns a single RxJava object, in which the list of user repositories is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>>

    /**
     * Method for getting user information
     *
     * @param - A class with user data
     *
     * @return The method returns a single RxJava object, in which the user info is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    fun getUserByLogin(user: GithubUserDTO): Single<GithubUserDTO>
}

class GithubUserRepositoryImpl @Inject constructor() : GithubUserRepository {

    /**
     * Method for getting a list of user's repositories.
     * @param user - A class with user data
     * @exception IllegalStateException - The method throws an exception if the user does not have a link to the list of repositories
     * @return The method returns a single RxJava object, in which the list of user repositories is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    override fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> =
        Single.just<List<GithubRepositoryDTO>>(listOf()).subscribeOn(Schedulers.io())

    /**
     * Method for getting user information
     *
     * @param - A class with user data
     *
     * @return The method returns a single RxJava object, in which the user info is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    override fun getUserByLogin(user: GithubUserDTO): Single<GithubUserDTO> =
        Single.just(
            GithubUserDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        ).subscribeOn(Schedulers.io())
}