package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubUserToRoomGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


interface UsersCache {
    fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
    ): Single<List<GithubUserDTO>>

    fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
    ): Single<GithubUserDTO>
}

class RoomGithubUsersCacheImpl @Inject constructor(private val localDatabase: GithubRoomDatabase) : UsersCache {

    override fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
    ): Single<List<GithubUserDTO>> = Single.fromCallable {
        val roomUsers = users.map { user ->
            convertGithubUserToRoomGithubUser(user)
        }
        localDatabase.userDao.insert(roomUsers)
        users
    }

    override fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
    ): Single<GithubUserDTO> = Single.fromCallable {
        val roomUsers = convertGithubUserToRoomGithubUser(githubUser)
        localDatabase.userDao.insert(roomUsers)
        githubUser
    }

}