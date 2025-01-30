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
    context: Context // Context is required to access the ConnectivityManager service.
) : InternetStatusService {

    // The ConnectivityManager is used to check network connectivity status and capabilities.
    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!

    // Checks if the device has an internet connection (either Wi-Fi, cellular, or Ethernet).
    override suspend fun theresInternet(): Boolean {
        // Get the active network and its capabilities
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        // Check if the network is capable of providing internet access
        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || // Wi-Fi
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || // Cellular
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) // Ethernet
                )
    }

    // Observes changes in the network status and emits the current status as a Flow.
    override val observeInternetStatus: Flow<InternetStatus>
        get() = callbackFlow {
            // Define a network callback to monitor network availability and loss.
            val callbacks = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(InternetStatus.HAVE_CONNECTION) // Send internet connection status.
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(InternetStatus.LOST_CONNECTION) // Send lost connection status.
                }
            }

            // Register the callback to listen for changes in the network status.
            connectivityManager.registerDefaultNetworkCallback(callbacks)

            // Clean up by unregistering the callback when the Flow is closed or canceled.
            awaitClose { connectivityManager.unregisterNetworkCallback(callbacks) }
        }
}

