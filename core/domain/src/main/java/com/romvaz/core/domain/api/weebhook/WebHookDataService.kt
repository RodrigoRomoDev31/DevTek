package com.romvaz.core.domain.api.weebhook

import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel

// This interface defines the contract for services that handle the logic for interacting with WebHook APIs.
// It includes methods for sending help and location data, wrapping the result in a
// `Result` wrapper to handle success and failure states.
interface WebHookDataService {

    // Sends help data using the provided `SendHelpPostModel`.
    suspend fun sendHelp(sendHelpPostModel: SendHelpPostModel): Result<WebHookResponseModel>

    // Sends location data using the provided `SendLocationPostModel`.
    suspend fun sendLocation(sendLocationPostModel: SendLocationPostModel): Result<WebHookResponseModel>
}
