package com.romvaz.feature.home.main.middlewares

import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.models.datastore.createPostModel
import com.romvaz.core.store.ActionSideEffect
import com.romvaz.core.ui.components.SnackBarTopStatus
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Trigger by user action
 * Send a Help request to external server
 * The result is used to update the state to show user if request sent correctly
 */
class SendHelpMiddleware @Inject constructor(
    private val webHookDataService: WebHookDataService
) : ActionSideEffect<MainScreenUiState, MainScreenAction>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observe(
        actionsFlow: Flow<MainScreenAction>,
        currentState: () -> MainScreenUiState
    ): Flow<MainScreenAction> =
        actionsFlow
            .filter { it is MainScreenAction.SendHelp }
            .map {
                webHookDataService.sendHelp(
                    currentState().userInfo?.createPostModel(
                        lat = currentState().userLocationRoute.lastOrNull()?.latitude?: 0.0,
                        lng = currentState().userLocationRoute.lastOrNull()?.longitude?: 0.0,
                        problem = currentState().problem
                    )!!
                )
            }
            .flatMapConcat { result ->
                result.fold(
                    onSuccess = {
                        flowOf(
                            if (it.success)
                                MainScreenAction.OnSendHelp(true, SnackBarTopStatus.SUCCESS)
                            else
                                MainScreenAction.OnSendHelp(false, SnackBarTopStatus.SUCCESS)
                        )
                    },
                    onFailure = { exception ->
                        flowOf(MainScreenAction.OnErrorInSendHelp(exception))
                    }
                )
            }
}

