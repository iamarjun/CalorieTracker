package com.arjun.onboarding_presentation.nutrition_goal

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.core_ui.LocalSpacing
import com.arjun.onboarding_domain.contract.nutrition_goal.NutrientGoalContract
import com.arjun.onboarding_presentation.OnboardingNavigator
import com.arjun.onboarding_presentation.R
import com.arjun.onboarding_presentation.components.ActionButton
import com.arjun.onboarding_presentation.components.UnitTextField
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun NutrientGoalScreen(
    navigator: OnboardingNavigator,
    viewModel: NutrientGoalViewModel = hiltViewModel(),
    modifier: Modifier,
    scaffoldState: ScaffoldState
) {
    Scaffold(modifier = modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        val modifier = modifier.padding(it)

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val spacing = LocalSpacing.current

        LaunchedEffect(key1 = Unit) {
            viewModel.effect.collectLatest {
                when (it) {
                    is NutrientGoalContract.Effect.ShowToast -> {
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
                    text = stringResource(id = R.string.what_are_your_nutrient_goals),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier.height(spacing.spaceMedium))
                UnitTextField(
                    modifier = modifier,
                    value = state.proteinRatio.toString(),
                    onValueChange = {
                        viewModel.setEvent(
                            NutrientGoalContract.Event.OnProteinRatioChange(
                                it
                            )
                        )
                    },
                    unit = stringResource(id = R.string.percent_proteins)
                )
                Spacer(modifier.height(spacing.spaceMedium))
                UnitTextField(
                    modifier = modifier,
                    value = state.carbsRatio.toString(),
                    onValueChange = {
                        viewModel.setEvent(
                            NutrientGoalContract.Event.OnCarbsRatioChange(
                                it
                            )
                        )
                    },
                    unit = stringResource(id = R.string.percent_carbs)
                )
                Spacer(modifier.height(spacing.spaceMedium))
                UnitTextField(
                    modifier = modifier,
                    value = state.fatsRatio.toString(),
                    onValueChange = {
                        viewModel.setEvent(
                            NutrientGoalContract.Event.OnFatsRatioChange(
                                it
                            )
                        )
                    },
                    unit = stringResource(id = R.string.percent_fats)
                )
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