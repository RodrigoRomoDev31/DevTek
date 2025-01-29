package com.romvaz.core.data.implementation.api

import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.models.api.SendHelpPostModel
import com.romvaz.core.domain.models.api.SendHelpPostResponseModel
import com.romvaz.core.network.utils.asResult
import com.romvaz.core.network.utils.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WebHookDataImplementation(
    private val webHookApi: WebHookApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WebHookDataService {
    override suspend fun sendHelp(sendHelpPostModel: SendHelpPostModel): Result<SendHelpPostResponseModel> =
        withContext(dispatcher) {
            safeCall { webHookApi.sendHelp(sendHelpPostModel) }
                .asResult()
        }

}
