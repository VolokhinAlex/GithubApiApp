package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentRepoDetailsBinding
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class RepoDetailsFragmentTest {

    private lateinit var scenario: FragmentScenario<UserFragment>
    private lateinit var scenarioRepoDetailsFragment: FragmentScenario<RepoDetailsFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PopularLibrariesAndroid)
        scenarioRepoDetailsFragment =
            launchFragmentInContainer(themeResId = R.style.Theme_PopularLibrariesAndroid)
    }

    @Test
    fun `fragment is not null return true`() {
        scenarioRepoDetailsFragment.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun `fragment text views are not null return true`() {
        scenarioRepoDetailsFragment.onFragment {
            val binding = FragmentRepoDetailsBinding.inflate(it.layoutInflater)
            assertThat(binding.forkCount).isNotNull()
            assertThat(binding.labelCommits).isNotNull()
            assertThat(binding.labelForkCount).isNotNull()
        }
    }

    @Test
    fun `fragment button are visible return true`() {
        scenarioRepoDetailsFragment.onFragment {
            val binding = FragmentRepoDetailsBinding.inflate(it.layoutInflater)
            assertThat(binding.openInBrowser.visibility).isEqualTo(View.VISIBLE)
        }
    }

    @Test
    fun `fragment labels has text return true`() {
        scenarioRepoDetailsFragment.onFragment {
            val binding = FragmentRepoDetailsBinding.inflate(it.layoutInflater)
            assertThat(binding.labelCommits.text).isEqualTo(it.getString(R.string.commits))
            assertThat(binding.labelForkCount.text).isEqualTo(it.getString(R.string.fork_count))
        }
    }

    @After
    fun tearDown() {
        scenarioRepoDetailsFragment.close()
        scenario.close()
    }
}