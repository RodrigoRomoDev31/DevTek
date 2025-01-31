package com.romvaz.core.data.implementation.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.domain.network.InternetStatusService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

// Implementation of InternetStatusService to check and observe internet connection status.
class InternetStatusImplementation(
    context: Context
) : InternetStatusService {

    // The ConnectivityManager is used to check network connectivity status and capabilities.
    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!


    /**
     * Checks if the device has an internet connection (either Wi-Fi, cellular, or Ethernet).
     * Get the active network and its capabilities
     * Check if the network is capable of providing internet access
     */
    override suspend fun theresInternet(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }


    /**
     * Observes changes in the network status and emits the current status as a Flow.
     * Define a network callback to monitor network availability and loss.
     * Register the callback to listen for changes in the network status.
     * Clean up by unregistering the callback when the Flow is closed or canceled.
     */
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

