package com.volokhinaleksey.popularlibrariesandroid.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubUserToRoomGithubUser
import io.reactivex.rxjava3.core.Single
import java.io.FileOutputStream
import java.net.URL


interface UsersCache {
    fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
        localDatabase: GithubRoomDatabase
    ): Single<List<GithubUserDTO>>

    fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
        localDatabase: GithubRoomDatabase
    ): Single<GithubUserDTO>
}

class RoomGithubUsersCacheImpl : UsersCache {

    override fun cacheUsersToDatabase(
        users: List<GithubUserDTO>,
        localDatabase: GithubRoomDatabase
    ): Single<List<GithubUserDTO>> = Single.fromCallable {
        val roomUsers = users.map { user ->
            user.imageUrlFromStorage = cacheImageToLocalDatabase(user)
            convertGithubUserToRoomGithubUser(user)
        }
        localDatabase.userDao.insert(roomUsers)
        users
    }

    override fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
        localDatabase: GithubRoomDatabase
    ): Single<GithubUserDTO> = Single.fromCallable {
        githubUser.imageUrlFromStorage = cacheImageToLocalDatabase(githubUser)
        val roomUsers = convertGithubUserToRoomGithubUser(githubUser)
        localDatabase.userDao.insert(roomUsers)
        githubUser
    }

}

private fun cacheImageToLocalDatabase(githubUser: GithubUserDTO): String {
    try {
        val url = URL(githubUser.avatarUrl)
        val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        val imageName = githubUser.avatarUrl?.substring(githubUser.avatarUrl.indexOf("u/") + 2)
        val path = "${App.appInstance.cacheDir}/$imageName.png"
        val outPutStream = FileOutputStream(path)
        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream)) {
            return path
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return ""
}