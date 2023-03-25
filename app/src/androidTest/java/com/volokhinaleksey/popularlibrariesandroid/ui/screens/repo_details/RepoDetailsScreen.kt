package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.popularlibrariesandroid.R
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object RepoDetailsScreen : KScreen<RepoDetailsScreen>() {

    override val layoutId: Int = R.layout.fragment_repo_details
    override val viewClass: Class<*> = RepoDetailsFragment::class.java

    val repoName = KTextView { withId(R.id.repo_name) }

    val openInBrowserBtn = KButton { withId(R.id.open_in_browser) }

    val labelForkCount = KTextView { withId(R.id.label_fork_count) }

    val forkCount = KTextView { withId(R.id.fork_count) }

    val labelCommits = KTextView { withId(R.id.label_commits) }

    val commitsList = KRecyclerView(builder = { withId(R.id.commits_list_container) }, {})

    val errorMessage = KTextView { withId(R.id.error_message) }
}