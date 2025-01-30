package com.romvaz.core.domain.network

import com.romvaz.core.domain.models.network.InternetStatus
import kotlinx.coroutines.flow.Flow

// This interface defines the contract for checking and observing internet connectivity status.
interface InternetStatusService {

    // This suspend function checks if the device has internet connectivity.
    // It returns a Boolean value:
    //   - `true` if there is an active internet connection.
    //   - `false` if there is no internet connection.
    suspend fun theresInternet(): Boolean

    // A Flow that emits the current internet status:
    //   - `InternetStatus.HAVE_CONNECTION` when the device has an internet connection.
    //   - `InternetStatus.LOST_CONNECTION` when the device loses its internet connection.
    val observeInternetStatus: Flow<InternetStatus>
}
