package com.volokhinaleksey.popularlibrariesandroid.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Interface for getting information about the network
 * The interface returns either once the availability of the network.
 * Or returns an object that can be monitored when subscribing
 */

interface NetworkStatus {

    fun isNetworkAvailable() : Observable<Boolean>
    fun isNetworkAvailableSingle() : Single<Boolean>

}