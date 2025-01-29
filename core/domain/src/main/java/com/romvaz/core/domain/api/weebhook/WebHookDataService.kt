package com.romvaz.core.domain.api.weebhook

import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel

interface WebHookDataService {
    suspend fun sendHelp(sendHelpPostModel: SendHelpPostModel): Result<WebHookResponseModel>
    suspend fun sendLocation(sendLocationPostModel: SendLocationPostModel): Result<WebHookResponseModel>
}
