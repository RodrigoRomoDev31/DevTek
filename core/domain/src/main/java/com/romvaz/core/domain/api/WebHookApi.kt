package com.romvaz.core.domain.api

import com.romvaz.core.domain.SEND_HELP_POST
import com.romvaz.core.domain.SEND_LOCATION_POST
import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebHookApi {
    @POST(SEND_HELP_POST)
    suspend fun sendHelp(@Body request: SendHelpPostModel): Response<WebHookResponseModel>

    @POST(SEND_LOCATION_POST)
    suspend fun sendLocation(@Body request: SendLocationPostModel): Response<WebHookResponseModel>
}
