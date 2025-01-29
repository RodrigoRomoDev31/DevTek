package com.romvaz.core.domain.api.weebhook

import com.romvaz.core.domain.models.api.SendHelpPostModel
import com.romvaz.core.domain.models.api.SendHelpPostResponseModel

interface WebHookDataService {
    suspend fun sendHelp(sendHelpPostModel: SendHelpPostModel): Result<SendHelpPostResponseModel>
}
