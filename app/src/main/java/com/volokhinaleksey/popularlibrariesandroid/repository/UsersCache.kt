package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubUserToRoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.utils.convertRoomGithubUserToGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


interface UsersCache {
    fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
    ): Single<List<GithubUserDTO>>

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
            convertGithubUserToRoomGithubUser(user)
        }
        localDatabase.userDao.upsert(roomUsers)
        users
    }

    override fun getUsersDataFromDatabase(): Single<List<GithubUserDTO>> =
        Single.fromCallable {
            localDatabase.userDao.getAll().map { roomGithubUser ->
                convertRoomGithubUserToGithubUser(roomGithubUser)
            }
        }

}