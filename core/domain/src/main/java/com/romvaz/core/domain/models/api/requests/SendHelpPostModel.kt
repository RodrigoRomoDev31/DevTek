package com.romvaz.core.domain.models.api.requests

import kotlinx.serialization.SerialName

data class SendHelpPostModel(
    @SerialName("operatorName") val operatorName: String,
    @SerialName("operatorId") val operatorId: String,
    @SerialName("operatorPhone") val operatorPhone: String,
    @SerialName("operatorTruckId") val operatorTruckId: String
)
