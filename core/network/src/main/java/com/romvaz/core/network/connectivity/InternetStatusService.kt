package com.romvaz.core.network.connectivity

import kotlinx.coroutines.flow.Flow

interface InternetStatusService {
    suspend fun theresInternet(): Boolean
    val observeInternetStatus: Flow<InternetStatus>
}

enum class InternetStatus {
    LOST_CONNECTION, HAVE_CONNECTION, UNAVAILABLE_CONNECTION
}
