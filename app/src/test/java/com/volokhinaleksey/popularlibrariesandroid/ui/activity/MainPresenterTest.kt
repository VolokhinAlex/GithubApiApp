package com.volokhinaleksey.popularlibrariesandroid.ui.activity

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: IScreens

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = MainPresenter(router, screens)
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        presenter.backClicked()
        verify(router, times(1)).exit()
    }
}