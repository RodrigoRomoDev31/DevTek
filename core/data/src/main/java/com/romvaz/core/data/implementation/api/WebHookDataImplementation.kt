package com.romvaz.core.data.implementation.api

import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel
import com.romvaz.core.network.utils.asResult
import com.romvaz.core.network.utils.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Implementation of WebHookDataService that interacts with the WebHookApi
class WebHookDataImplementation(
    // The API interface used for making network requests
    private val webHookApi: WebHookApi,
    // CoroutineDispatcher to define the thread pool for network operations
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WebHookDataService {

    // Sends help-related data to the webhook endpoint.
    // Uses the safeCall utility to handle potential errors and return a result wrapped in a 'Result' object.
    override suspend fun sendHelp(sendHelpPostModel: SendHelpPostModel): Result<WebHookResponseModel> =
        withContext(dispatcher) { // Ensures the network request runs on the specified dispatcher (background thread)
            safeCall { webHookApi.sendHelp(sendHelpPostModel) } // Calls the API method and handles errors safely
                .asResult() // Converts the result into a 'Result' object to indicate success or failure
        }

    // Sends location data to the webhook endpoint.
    override suspend fun sendLocation(sendLocationPostModel: SendLocationPostModel): Result<WebHookResponseModel> =
        withContext(dispatcher) {
            safeCall { webHookApi.sendLocation(sendLocationPostModel) }
        }.asResult()
}
