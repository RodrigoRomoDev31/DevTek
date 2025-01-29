package com.romvaz.core.domain.models.api.requests

import kotlinx.serialization.SerialName

data class SendLocationPostModel(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("timeStamp") val timeStamp: Long,
    @SerialName("accuracy") val accuracy: Float,
)
