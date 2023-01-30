package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import com.volokhinaleksey.popularlibrariesandroid.utils.convertRoomGithubUserToGithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Interface for getting data from a remote repository
 */

interface GithubUsersRepository {
    /**
     * Method for getting a list of users
     */

    fun getUsers(): Single<List<GithubUserDTO>>

    /**
     * Method for getting user information
     * @param user - User login
     */

    fun getUserByLogin(user: GithubUserDTO): Single<GithubUserDTO>
}

/**
 * Implementation of the interface for getting data from a remote repository
 */

class GithubUsersRepositoryImpl @Inject constructor(
    private val remoteApiSource: GithubApiService,
    private val networkStatus: NetworkStatus,
    private val localDatabase: GithubRoomDatabase,
    private val roomGithubUsersCache: UsersCache
) : GithubUsersRepository {

    /**
     * Method for getting a list of users
     */

    override fun getUsers(): Single<List<GithubUserDTO>> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                remoteApiSource.getUsers().flatMap { users ->
                    roomGithubUsersCache.cacheUsersToDatabase(users)
                }
            } else {
                Single.fromCallable {
                    localDatabase.userDao.getAll().map { roomGithubUser ->
                        convertRoomGithubUserToGithubUser(roomGithubUser)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())

    /**
     * Method for getting user information
     * @param user - User data
     */

    override fun getUserByLogin(user: GithubUserDTO): Single<GithubUserDTO> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                remoteApiSource.getUser(user.url.orEmpty()).flatMap { githubUser ->
                    roomGithubUsersCache.cacheUserToDatabase(githubUser)
                }
            } else {
                Single.fromCallable {
                    localDatabase.userDao.findByLogin(user.login.orEmpty())
                        ?.let { convertRoomGithubUserToGithubUser(it) }
                }
            }
        }.subscribeOn(Schedulers.io())
}