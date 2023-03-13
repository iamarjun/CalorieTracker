package com.arjun.onboarding_presentation.age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.core_ui.LocalSpacing
import com.arjun.onboarding_domain.contract.age.AgeContract
import com.arjun.onboarding_presentation.R
import com.arjun.onboarding_presentation.components.ActionButton
import com.arjun.onboarding_presentation.components.UnitTextField
import com.arjun.onboarding_presentation.destinations.AgeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun AgeScreen(
    navigator: DestinationsNavigator,
    viewModel: AgeViewModel,
    _modifier: Modifier,
    scaffoldState: ScaffoldState
) {
    Scaffold(modifier = _modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        val modifier = _modifier.padding(it)

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val spacing = LocalSpacing.current

        LaunchedEffect(key1 = Unit) {
            viewModel.effect.collectLatest {
                when (it) {
                    is AgeContract.Effect.ShowToast -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message)
                    }
                }
            }
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge)
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = modifier,
                    text = stringResource(id = R.string.whats_your_age),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier.height(spacing.spaceMedium))
                UnitTextField(
                    modifier = modifier,
                    value = state.age.toString(),
                    onValueChange = { viewModel.setEvent(AgeContract.Event.OnAgeChange(it)) },
                    unit = stringResource(id = R.string.years)
                )
            }
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = { navigator.navigate(AgeScreenDestination) },
                modifier = modifier.align(Alignment.BottomEnd),
                isEnabled = true
            )
        }

    }
}