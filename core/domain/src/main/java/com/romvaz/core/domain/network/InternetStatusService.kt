package com.romvaz.core.domain.network

import com.romvaz.core.domain.models.network.InternetStatus
import kotlinx.coroutines.flow.Flow

interface InternetStatusService {
    suspend fun theresInternet(): Boolean
    val observeInternetStatus: Flow<InternetStatus>
}
