package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubRepositoryToRoomGithubUserRepo

interface RepositoriesCache {
    fun cacheUserRepositoriesToDatabase(
        localDatabase: GithubRoomDatabase,
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): List<GithubRepositoryDTO>
}

class RoomGithubRepositoriesCacheImpl : RepositoriesCache {

    override fun cacheUserRepositoriesToDatabase(
        localDatabase: GithubRoomDatabase,
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): List<GithubRepositoryDTO> {
        userDTO.login?.let {
            val roomUser = localDatabase.userDao.findByLogin(it)
            val roomRepos = repositories.map { user ->
                convertGithubRepositoryToRoomGithubUserRepo(user, roomUser?.id)
            }
            localDatabase.repositoryDao.insert(roomRepos)
        }
        return repositories
    }

}