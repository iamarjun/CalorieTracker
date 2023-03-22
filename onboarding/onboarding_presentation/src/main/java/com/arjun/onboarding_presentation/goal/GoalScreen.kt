package com.arjun.onboarding_presentation.goal

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.core.domain.model.Goal
import com.arjun.core_ui.LocalSpacing
import com.arjun.onboarding_domain.contract.goal.GoalsContract
import com.arjun.onboarding_presentation.R
import com.arjun.onboarding_presentation.components.ActionButton
import com.arjun.onboarding_presentation.components.SelectableButton
import com.arjun.onboarding_presentation.destinations.AgeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun GoalScreen(
    navigator: DestinationsNavigator,
    viewModel: GoalViewModel,
    _modifier: Modifier,
    scaffoldState: ScaffoldState
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
                    text = stringResource(id = R.string.what_are_your_nutrient_goals),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier.height(spacing.spaceMedium))
                Row(modifier = modifier) {
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.lose),
                        isSelected = state.goal is Goal.LoseWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                GoalsContract.Event.OnGoalChange(
                                    Goal.LoseWeight
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.keep),
                        isSelected = state.goal is Goal.MaintainWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                GoalsContract.Event.OnGoalChange(
                                    Goal.MaintainWeight
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        modifier = modifier,
                        text = stringResource(id = R.string.gain),
                        isSelected = state.goal is Goal.GainWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.setEvent(
                                GoalsContract.Event.OnGoalChange(
                                    Goal.GainWeight
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                    )
                }
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