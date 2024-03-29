package com.arjun.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.core.domain.model.ActivityLevel
import com.arjun.core_ui.LocalSpacing
import com.arjun.onboarding_domain.contract.activity.ActivityContract
import com.arjun.onboarding_presentation.OnboardingNavigator
import com.arjun.onboarding_presentation.R
import com.arjun.onboarding_presentation.components.ActionButton
import com.arjun.onboarding_presentation.components.SelectableButton
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ActivityScreen(
    navigator: OnboardingNavigator,
    _modifier: Modifier,
    scaffoldState: ScaffoldState,
    viewModel: ActivityViewModel = hiltViewModel()
) {

    Scaffold(modifier = _modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        val modifier = _modifier.padding(it)

        val spacing = LocalSpacing.current
        val state by viewModel.uiState.collectAsStateWithLifecycle()

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
                    text = stringResource(id = R.string.whats_your_activity_level),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier.height(spacing.spaceMedium))
                Row(modifier = modifier) {
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.low),
                        isSelected = state.activityLevel is ActivityLevel.Low,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                ActivityContract.Event.OnActivityChange(
                                    ActivityLevel.Low
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.medium),
                        isSelected = state.activityLevel is ActivityLevel.Medium,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                ActivityContract.Event.OnActivityChange(
                                    ActivityLevel.Medium
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.high),
                        isSelected = state.activityLevel is ActivityLevel.High,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                ActivityContract.Event.OnActivityChange(
                                    ActivityLevel.High
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                }
            }
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = { navigator.navigateToNextScreen() },
                modifier = modifier.align(Alignment.BottomEnd),
                isEnabled = true
            )
        }
    }
}