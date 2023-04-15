package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.popularlibrariesandroid.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView

object UserScreen : KScreen<UserScreen>() {

    override val layoutId: Int = R.layout.fragment_user
    override val viewClass: Class<*> = UserFragment::class.java

    val userImage = KImageView { withId(R.id.user_image) }

    val userLogin = KTextView { withId(R.id.user_login) }

    val personName = KTextView { withId(R.id.person_name) }

    val labelRepositories = KTextView { withId(R.id.label_repositories) }

    val publicRepos = KTextView { withId(R.id.public_repos) }

    val labelPublicGists = KTextView { withId(R.id.label_public_gists) }

    val publicGists = KTextView { withId(R.id.public_gists) }

    val labelFollowers = KTextView { withId(R.id.label_followers) }

    val followers = KTextView { withId(R.id.followers) }

    val labelFollowing = KTextView { withId(R.id.label_following) }

    val following = KTextView { withId(R.id.following) }

    val labelRepositoriesList = KTextView { withId(R.id.label_repositories_list) }

    val reposList = KRecyclerView(builder = { withId(R.id.repos_list_container) }, {})

    val errorMessage = KTextView { withId(R.id.error_message) }
}