package com.romvaz.core.domain.models.api.response

import kotlinx.serialization.SerialName

//Response body for all requests with webhook
data class WebHookResponseModel(
    @SerialName("success") val success: Boolean
)
