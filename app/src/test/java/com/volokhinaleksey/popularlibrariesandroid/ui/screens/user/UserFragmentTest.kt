package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUserBinding
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserFragmentTest {

    private lateinit var scenario: FragmentScenario<UserFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun `fragment is not null return true`() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun `fragment text views are not null return true`() {
        scenario.onFragment {
            val binding = FragmentUserBinding.inflate(it.layoutInflater)
            assertThat(binding.userLogin).isNotNull()
            assertThat(binding.personName).isNotNull()
            assertThat(binding.publicRepos).isNotNull()
            assertThat(binding.publicGists).isNotNull()
            assertThat(binding.followers).isNotNull()
            assertThat(binding.following).isNotNull()
            assertThat(binding.labelRepositories).isNotNull()
            assertThat(binding.labelRepositoriesList).isNotNull()
        }
    }

    @Test
    fun `fragment text views are visible return true`() {
        scenario.onFragment {
            val binding = FragmentUserBinding.inflate(it.layoutInflater)
            assertThat(binding.userLogin.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.personName.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.publicRepos.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.publicGists.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.followers.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.following.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.labelRepositories.visibility).isEqualTo(View.VISIBLE)
            assertThat(binding.labelRepositoriesList?.visibility).isEqualTo(View.VISIBLE)
        }
    }

    @Test
    fun `fragment labels has text return true`() {
        scenario.onFragment {
            val binding = FragmentUserBinding.inflate(it.layoutInflater)
            assertThat(binding.labelFollowers.text).isEqualTo(it.getString(R.string.followers))
            assertThat(binding.labelFollowing.text).isEqualTo(it.getString(R.string.following))
            assertThat(binding.labelPublicGists.text).isEqualTo(it.getString(R.string.gists))
            assertThat(binding.labelRepositories.text).isEqualTo(it.getString(R.string.repositories))
            assertThat(binding.labelRepositoriesList?.text).isEqualTo(it.getString(R.string.repositories))
        }
    }

    @Test
    fun `fragment image view is not null return true`() {
        scenario.onFragment {
            val binding = FragmentUserBinding.inflate(it.layoutInflater)
            assertThat(binding.userImage).isNotNull()
        }
    }

    @Test
    fun `fragment image view is visible return true`() {
        scenario.onFragment {
            val binding = FragmentUserBinding.inflate(it.layoutInflater)
            assertThat(binding.userImage.visibility).isEqualTo(View.VISIBLE)
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}