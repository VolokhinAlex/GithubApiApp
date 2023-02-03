package com.volokhinaleksey.popularlibrariesandroid.ui.images

interface ImageLoader<T> {
    fun loadImage(url: String, target: T)
}