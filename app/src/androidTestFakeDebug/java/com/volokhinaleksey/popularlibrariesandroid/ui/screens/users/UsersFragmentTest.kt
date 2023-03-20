package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersFragmentTest {

    private lateinit var scenario: FragmentScenario<UsersFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
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
        onView(withId(R.id.users_list_container)).check(matches(withEffectiveVisibility(Visibility
            .VISIBLE)))
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() {
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}