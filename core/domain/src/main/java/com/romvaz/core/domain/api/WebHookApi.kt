package com.romvaz.core.domain.api

import com.romvaz.core.domain.SEND_HELP_POST
import com.romvaz.core.domain.SEND_LOCATION_POST
import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// This interface defines the contract for the WebHook API, which allows sending help and location data
// to a remote server using HTTP POST requests. The methods are asynchronous and use Retrofit to handle
// the network requests.
interface WebHookApi {

    // Sends help data to the server.
    @POST(SEND_HELP_POST)
    suspend fun sendHelp(@Body request: SendHelpPostModel): Response<WebHookResponseModel>

    // Sends location data to the server.
    @POST(SEND_LOCATION_POST)
    suspend fun sendLocation(@Body request: SendLocationPostModel): Response<WebHookResponseModel>
}
