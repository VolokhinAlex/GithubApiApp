package com.volokhinaleksey.popularlibrariesandroid.ui.images

interface ImageLoader<T> {

    /**
     * Method for getting and inserting an image into a container
     *
     * @param url - Image url to get it
     * @param target - Image container to insert it
     */

    fun loadImage(url: String, target: T)
}