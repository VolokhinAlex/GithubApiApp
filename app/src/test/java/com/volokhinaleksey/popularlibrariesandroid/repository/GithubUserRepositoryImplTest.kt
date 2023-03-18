package com.volokhinaleksey.popularlibrariesandroid.repository

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class GithubUserRepositoryImplTest {

    private lateinit var repository: GithubUserRepository

    @Mock
    private lateinit var apiService: GithubApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FakeGithubUserRepository(GithubApiHolder(apiService))
    }

    @Test
    fun `checking method calls getUserRepos() return true`() {
        Mockito.`when`(apiService.getUserRepos("")).thenReturn(Single.just(emptyList()))
        repository.getUserRepos(mock())
        verify(apiService, times(1)).getUserRepos("")
    }

    @Test
    fun `checking the receipt of data from the getUserRepos() method return true`() {
        val mockSingleGithubRepository = Single.just(listOf(mock(GithubRepositoryDTO::class.java)))
        Mockito.`when`(apiService.getUserRepos("")).thenReturn(mockSingleGithubRepository)
        assertThat(repository.getUserRepos(mock())).isEqualTo(mockSingleGithubRepository)
    }

    @Test
    fun `checking method calls getUserByLogin() return true`() {
        Mockito.`when`(apiService.getUserData("")).thenReturn(Single.just(mock()))
        repository.getUserByLogin(mock())
        verify(apiService, times(1)).getUserData("")
    }

    @Test
    fun `checking the receipt of data from the getUserByLogin() method return true`() {
        val mockGithubUserDTO = Single.just(mock(GithubUserDTO::class.java))
        Mockito.`when`(apiService.getUserData("")).thenReturn(mockGithubUserDTO)
        assertThat(repository.getUserByLogin(mock())).isEqualTo(mockGithubUserDTO)
    }

}