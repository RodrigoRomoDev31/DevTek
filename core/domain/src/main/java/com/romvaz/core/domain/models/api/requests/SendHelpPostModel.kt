package com.romvaz.core.domain.models.api.requests

import kotlinx.serialization.SerialName

// Request Model for send help
data class SendHelpPostModel(
    @SerialName("operatorName") val operatorName: String,
    @SerialName("operatorId") val operatorId: String,
    @SerialName("operatorPhone") val operatorPhone: String,
    @SerialName("operatorTruckId") val operatorTruckId: String
)
