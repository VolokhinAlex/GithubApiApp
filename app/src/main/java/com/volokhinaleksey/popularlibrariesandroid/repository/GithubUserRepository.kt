package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
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

class GithubUserRepositoryImpl @Inject constructor(
    private val remoteApiSource: ApiHolder,
    private val networkStatus: NetworkStatus,
    private val userCache: UserCache,
) : GithubUserRepository {

    /**
     * Method for getting a list of user's repositories.
     * @param user - A class with user data
     * @exception IllegalStateException - The method throws an exception if the user does not have a link to the list of repositories
     * @return The method returns a single RxJava object, in which the list of user repositories is wrapped.
     * If the user has access to the Internet, the data will be requested via the API, if there is no access to the Internet,
     * then all data will be taken from the local database, if they are there
     */

    override fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                user.reposUrl?.let { url ->
                    remoteApiSource.apiService.getUserRepos(url)
                        .flatMap { repositories ->
                            userCache.cacheUserRepositoriesToDatabase(
                                repositories,
                                user
                            )
                        }
                } ?: error("User has no repos url")
            } else {
                userCache.getUserRepositoriesFromDatabase(user)
            }
        }.subscribeOn(Schedulers.io())

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
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                remoteApiSource.apiService.getUserData(user.url.orEmpty()).flatMap { githubUser ->
                    userCache.cacheUserToDatabase(githubUser)
                }
            } else {
                userCache.getUserDataFromDatabase(user)
            }
        }.subscribeOn(Schedulers.io())
}