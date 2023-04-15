package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import com.volokhinaleksey.popularlibrariesandroid.delay
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersFragmentTest {

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
    fun usersList_CheckVisible_ReturnTrue() {
        onView(withId(R.id.users_list_container)).check(
            matches(
                withEffectiveVisibility(
                    Visibility
                        .VISIBLE
                )
            )
        )
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() {
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun check_usersList_scrollTo_ReturnTrue() {
        onView(withId(R.id.users_list_container))
            .perform(
                RecyclerViewActions.scrollTo<UsersAdapter.ViewHolder>(
                    hasDescendant(
                        withText("User 0")
                    )
                )
            )
    }

    @Test
    fun check_usersList_ClickOnItem_ReturnTrue() {
        onView(withId(R.id.users_list_container)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.ViewHolder>(
                3,
                click()
            )
        )
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}