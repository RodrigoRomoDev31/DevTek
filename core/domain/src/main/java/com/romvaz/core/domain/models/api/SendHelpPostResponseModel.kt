package com.romvaz.core.domain.models.api

import kotlinx.serialization.SerialName

data class SendHelpPostResponseModel(
    @SerialName("success") val success: Boolean
)
