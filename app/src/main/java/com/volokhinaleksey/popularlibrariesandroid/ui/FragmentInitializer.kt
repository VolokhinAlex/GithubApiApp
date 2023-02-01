package com.volokhinaleksey.popularlibrariesandroid.ui

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

const val DATA_KEY = "DATA KEY"

interface FragmentInitializer<T : Parcelable> {

    fun newInstance(arg: T): Fragment {
        val declaringClassName = this::class.java.declaringClass.name
        return FragmentFactory().instantiate(
            this::class.java.declaringClass?.classLoader!!,
            declaringClassName
        ).apply {
            arguments = bundleOf(
                DATA_KEY to arg
            )
        }
    }

}