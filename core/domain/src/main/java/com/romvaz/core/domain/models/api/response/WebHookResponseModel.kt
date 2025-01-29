package com.romvaz.core.domain.models.api.response

import kotlinx.serialization.SerialName

data class WebHookResponseModel(
    @SerialName("success") val success: Boolean
)
