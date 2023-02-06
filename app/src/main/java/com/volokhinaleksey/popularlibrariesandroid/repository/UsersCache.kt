package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToRoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


interface UsersCache {

    /**
     * The method writes a list of users to the database
     *
     * @param users - A class with the list of user data
     *
     * @return - The method returns a single RxJava object, in which the list of users is wrapped.
     */

    fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
    ): Single<List<GithubUserDTO>>

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @return - The method returns a single RxJava object, in which the list of users is wrapped.
     */

    fun getUsersDataFromDatabase(): Single<List<GithubUserDTO>>
}

/**
 * Implementation of an interface for caching data to a local sqlite database under Room management
 *
 * @param localDatabase - A local database instance that is automatically injected using dagger
 */

class RoomGithubUsersCacheImpl @Inject constructor(
    private val localDatabase: GithubRoomDatabase
) : UsersCache {

    /**
     * The method writes a list of users to the database
     *
     * @param users - A class with the list of user data
     *
     * @return - The method returns a single RxJava object, in which the list of users is wrapped.
     */

    override fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
    ): Single<List<GithubUserDTO>> = Single.fromCallable {
        val roomUsers = users.map { user ->
            mapToRoomGithubUser(githubUser = user)
        }
        localDatabase.userDao.upsert(roomUsers)
        users
    }

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @return - The method returns a single RxJava object, in which the list of users is wrapped.
     */

    override fun getUsersDataFromDatabase(): Single<List<GithubUserDTO>> =
        Single.fromCallable {
            localDatabase.userDao.getAll().map { roomGithubUser ->
                mapToGithubUser(roomGithubUser = roomGithubUser)
            }
        }

}