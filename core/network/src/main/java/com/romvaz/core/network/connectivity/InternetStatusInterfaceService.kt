package com.romvaz.core.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class InternetStatusInterfaceService(
    context: Context
) : InternetStatusInterface {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!

    override suspend fun theresInternet(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)

        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }

    override val observeInternetStatus: Flow<InternetStatus>
        get() = callbackFlow {
            val callbacks = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(InternetStatus.HAVE_CONNECTION)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(InternetStatus.LOST_CONNECTION)
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callbacks)
            awaitClose { connectivityManager.unregisterNetworkCallback(callbacks) }
        }
}

