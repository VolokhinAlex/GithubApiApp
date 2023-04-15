package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.popularlibrariesandroid.R
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView

object UsersScreen : KScreen<UsersScreen>() {

    override val layoutId: Int = R.layout.fragment_users
    override val viewClass: Class<*> = UsersFragment::class.java

    val usersList = KRecyclerView(builder = { withId(R.id.users_list_container) }, {})

    val errorMessage = KTextView { withId(R.id.error_message) }
}