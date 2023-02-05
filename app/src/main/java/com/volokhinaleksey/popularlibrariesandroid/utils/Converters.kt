package com.volokhinaleksey.popularlibrariesandroid.utils

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

fun convertGithubUserToRoomGithubUser(githubUser: GithubUserDTO): RoomGithubUser =
    RoomGithubUser(
        id = githubUser.id,
        login = githubUser.login,
        avatarUrl = githubUser.avatarUrl,
        publicRepos = githubUser.publicRepos,
        publicGists = githubUser.publicGists,
        followers = githubUser.followers,
        following = githubUser.following,
        name = githubUser.name,
        company = githubUser.company,
        blog = githubUser.blog,
        location = githubUser.location,
        url =  githubUser.url,
        reposUrl = githubUser.reposUrl
    )

fun convertRoomGithubUserToGithubUser(roomGithubUser: RoomGithubUser): GithubUserDTO =
    GithubUserDTO(
        login = roomGithubUser.login,
        id = roomGithubUser.id,
        avatarUrl = roomGithubUser.avatarUrl,
        publicRepos = roomGithubUser.publicRepos,
        publicGists = roomGithubUser.publicGists,
        followers = roomGithubUser.followers,
        following = roomGithubUser.following,
        name = roomGithubUser.name,
        company = roomGithubUser.company,
        blog = roomGithubUser.blog,
        location = roomGithubUser.location,
        url =  roomGithubUser.url,
        reposUrl = roomGithubUser.reposUrl
    )


fun convertGithubRepositoryToRoomGithubUserRepo(
    githubRepo: GithubRepositoryDTO,
    userId: Long?
): RoomGithubUserRepo =
    RoomGithubUserRepo(
        id = githubRepo.id ?: 0,
        userId = userId ?: 0,
        name = githubRepo.name ?: "",
        forksCount = githubRepo.forks ?: 0,
        htmlUrl = githubRepo.htmlUrl,
        fork = githubRepo.fork,
        createdAt = githubRepo.createdAt
    )

fun convertRoomGithubUserRepoToGithubRepository(roomGithubRepo: RoomGithubUserRepo): GithubRepositoryDTO =
    GithubRepositoryDTO(
        id = roomGithubRepo.id,
        name = roomGithubRepo.name,
        htmlUrl = roomGithubRepo.htmlUrl,
        fork = roomGithubRepo.fork,
        createdAt = roomGithubRepo.createdAt,
        forks = roomGithubRepo.forksCount
    )