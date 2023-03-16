package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import moxy.MvpPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class UsersPresenterTest {

    private lateinit var usersPresenter: MvpPresenter<UsersView>

    @Mock
    private lateinit var repository: GithubUsersRepository

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usersPresenter =
            UsersPresenter(
                repository,
                router,
                mock(IScreens::class.java),
                TestScheduler(),
                mock(UsersScopeContainer::class.java)
            )
    }

    @Test
    fun `checking method calls loadUsersData() return true`() {
        val mockView = mock(UsersView::class.java)
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        usersPresenter.attachView(mockView)
        verify(repository, times(1)).getUsers()
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        Mockito.`when`(router.exit()).then {}
        (usersPresenter as UsersPresenter).backPressed()
        verify(router, times(1)).exit()
    }

}