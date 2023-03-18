package com.volokhinaleksey.popularlibrariesandroid.repository

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory.times

class GithubUsersRepositoryImplTest {

    private lateinit var repository: GithubUsersRepository

    @Mock
    private lateinit var apiService: GithubApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FakeGithubUsersRepository(GithubApiHolder(apiService))
    }

    @Test
    fun `checking method calls getUsers() return true`() {
        Mockito.`when`(apiService.getUsersData()).thenReturn(Single.just(emptyList()))
        repository.getUsers()
        verify(apiService, times(1)).getUsersData()
    }

    @Test
    fun `checking the receipt of data from the method return true`() {
        val mockGithubUserDTO = Single.just(listOf(mock(GithubUserDTO::class.java)))
        Mockito.`when`(apiService.getUsersData()).thenReturn(mockGithubUserDTO)
        assertThat(repository.getUsers()).isEqualTo(mockGithubUserDTO)
    }

}