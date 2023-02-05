package com.volokhinaleksey.popularlibrariesandroid.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatus {

    fun isNetworkAvailable() : Observable<Boolean>
    fun isNetworkAvailableSingle() : Single<Boolean>

}