package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import org.junit.After
import org.junit.Before
import org.junit.Test

class UsersFragmentTest : TestCase() {

    private lateinit var scenario: FragmentScenario<UsersFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PopularLibrariesAndroid)
    }

    @Test
    fun fragment_NotNull_ReturnTrue() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun usersList_NotNull_ReturnTrue() {
        scenario.onFragment {
            val binding = FragmentUsersBinding.inflate(it.layoutInflater)
            assertThat(binding.usersListContainer).isNotNull()
        }
    }

    @Test
    fun usersList_CheckVisible_ReturnTrue() = run {
        UsersScreen {
            usersList {
                isVisible()
            }
        }
    }

    @Test
    fun check_usersList_scrollTo_ReturnTrue() = run {
        UsersScreen {
            usersList {
                scrollTo(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("caged")
                    )
                )
            }
        }
    }

    @Test
    fun check_usersList_ClickOnItem_ReturnTrue() = run {
        UsersScreen {
            usersList {
                scrollTo(3)
                click()
            }
        }
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() = run {
        UsersScreen {
            errorMessage {
                isGone()
            }
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}