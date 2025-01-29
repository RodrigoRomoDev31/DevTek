package com.romvaz.core.domain.api

import com.romvaz.core.domain.SEND_HELP_POST
import com.romvaz.core.domain.models.api.SendHelpPostModel
import com.romvaz.core.domain.models.api.SendHelpPostResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebHookApi {
    @POST(SEND_HELP_POST)
    suspend fun sendHelp(@Body request: SendHelpPostModel): Response<SendHelpPostResponseModel>
}
