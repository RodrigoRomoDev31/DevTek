package com.romvaz.core.domain.models.api.requests

import kotlinx.serialization.SerialName

data class CurrentLocationModel(
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
)
