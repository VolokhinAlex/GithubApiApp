package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class UsersPresenterTest {

    private lateinit var usersPresenter: UsersPresenter

    @Mock
    private lateinit var repository: GithubUsersRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var viewState: `UsersView$$State`

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        usersPresenter =
            UsersPresenter(
                repository,
                router,
                mock(IScreens::class.java),
                AndroidSchedulers.mainThread(),
                mock(UsersScopeContainer::class.java)
            )
        usersPresenter.setViewState(viewState)
    }

    @Test
    fun `checking method calls loadUsersData() return true`() {
        `when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        usersPresenter.attachView(mock())
        verify(repository, times(1)).getUsers()
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        usersPresenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test
    fun `checking the loading status return true`() {
        `when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        usersPresenter.attachView(mock())
        verify(viewState, atLeastOnce()).loadingState()
    }

    @Test
    fun `checking the init method call return true`() {
        `when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        usersPresenter.attachView(mock())
        verify(viewState, atLeastOnce()).init()
    }

    @Test
    fun `checking the error return from the method in the loadUsersData() method return true`() {
        `when`(repository.getUsers()).thenReturn(Single.error(RuntimeException("Something went wrong")))
        usersPresenter.attachView(mock())
        verify(viewState, atLeastOnce()).errorState("Something went wrong")
    }

    @Test
    fun `checking the success state return from the method in the loadUsersData() method return true`() {
        `when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        usersPresenter.attachView(mock())
        verify(viewState, atLeastOnce()).successState()
    }

    @After
    fun tearDown() {
        usersPresenter.detachView(mock())
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(null)
    }

}