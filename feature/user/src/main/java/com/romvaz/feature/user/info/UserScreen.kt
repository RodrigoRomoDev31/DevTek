package com.romvaz.feature.user.info

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romvaz.core.domain.models.HardUserPreferenceModel
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.DevTekHeader
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.components.LoadingComponent
import com.romvaz.core.ui.components.VerticalSpacer
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.h3
import com.romvaz.feature.user.components.UserInfoFieldComponent

@Composable
fun UserScreen(
    viewModel: UserScreenViewModel = hiltViewModel()
) {
    val state by viewModel.observeState().collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Content(
        userPreferenceModelState = state.userPreferenceModel,
        scrollState = scrollState,
        popBack = viewModel::popBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    userPreferenceModelState: HardUserPreferenceModel?,
    scrollState: ScrollState,
    popBack: () -> Unit
) {
    DevTekScaffold(
        header = {
            DevTekHeader(
                title = {
                    Text(
                        text = stringResource(R.string.user_info_tittle),
                        style = MaterialTheme.typography.h3.copy(MaterialTheme.colorScheme.onSurface)
                    )
                },
                primaryAction = {
                    IconButton(onClick = {
                        popBack()
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (userPreferenceModelState == null)
            LoadingComponent()
        else
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerticalSpacer(Spacings.four)
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                VerticalSpacer(Spacings.six)

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.name_title),
                    filedContent = userPreferenceModelState.name
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.email_title),
                    filedContent = userPreferenceModelState.email
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.phone_title),
                    filedContent = userPreferenceModelState.phoneNumber
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.user_id_title),
                    filedContent = userPreferenceModelState.conductorId
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.truck_model),
                    filedContent = userPreferenceModelState.truckModel
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.truck_plate),
                    filedContent = userPreferenceModelState.plate
                )

                UserInfoFieldComponent(
                    fieldType = stringResource(R.string.truck_id),
                    filedContent = userPreferenceModelState.truckId
                )
            }
    }
}
