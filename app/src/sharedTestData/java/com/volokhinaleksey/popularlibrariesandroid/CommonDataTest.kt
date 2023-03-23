package com.volokhinaleksey.popularlibrariesandroid

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers

internal const val TIMEOUT = 2000L

fun delay(): ViewAction {
    return object : ViewAction {
        override fun getConstraints() = ViewMatchers.isRoot()
        override fun getDescription(): String = "wait for $2 seconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(TIMEOUT)
        }
    }
}