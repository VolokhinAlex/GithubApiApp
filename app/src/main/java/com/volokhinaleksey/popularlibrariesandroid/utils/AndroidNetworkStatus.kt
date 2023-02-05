package com.volokhinaleksey.popularlibrariesandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Implementation of the interface for receiving data from the network on smartphones with Android OS
 */

class AndroidNetworkStatus(context: Context) : NetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)

        val connectivityManager = context.getSystemService<ConnectivityManager>()
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(request, object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                statusSubject.onNext(true)
            }

            override fun onUnavailable() {
                statusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                statusSubject.onNext(false)
            }
        })
    }

    override fun isNetworkAvailable(): Observable<Boolean> = statusSubject

    override fun isNetworkAvailableSingle(): Single<Boolean> = statusSubject.first(false)

}