package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter

import com.volokhinaleksey.popularlibrariesandroid.ui.items.ItemView

/**
 * Interface for interacting with UI elements of the list
 */

interface CommitsItemView : ItemView {

    fun setCommitterName(committerName: String)

    fun setCommitterEmail(committerEmail: String)

    fun setCommitDate(date: String)

    fun setCommitMessage(message: String)
}