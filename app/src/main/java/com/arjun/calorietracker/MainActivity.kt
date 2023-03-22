package com.arjun.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arjun.calorietracker.ui.theme.CalorieTrackerTheme
import com.arjun.onboarding_presentation.NavGraphs
import com.arjun.onboarding_presentation.activity.ActivityViewModel
import com.arjun.onboarding_presentation.age.AgeViewModel
import com.arjun.onboarding_presentation.destinations.ActivityScreenDestination
import com.arjun.onboarding_presentation.destinations.AgeScreenDestination
import com.arjun.onboarding_presentation.destinations.GenderScreenDestination
import com.arjun.onboarding_presentation.destinations.GoalScreenDestination
import com.arjun.onboarding_presentation.destinations.HeightScreenDestination
import com.arjun.onboarding_presentation.destinations.WeightScreenDestination
import com.arjun.onboarding_presentation.gender.GenderViewModel
import com.arjun.onboarding_presentation.goal.GoalViewModel
import com.arjun.onboarding_presentation.height.HeightViewModel
import com.arjun.onboarding_presentation.weight.WeightViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val activityLevelVm = viewModel<ActivityViewModel>()
                val goalVm = viewModel<GoalViewModel>()
                val genderVm = viewModel<GenderViewModel>()
                val heightVm = viewModel<HeightViewModel>()
                val weightVm = viewModel<WeightViewModel>()
                val ageVm = viewModel<AgeViewModel>()
                val modifier = Modifier
                val scaffoldState = rememberScaffoldState()
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(modifier)
                        dependency(scaffoldState)
                        dependency(GenderScreenDestination) { genderVm }
                        dependency(AgeScreenDestination) { ageVm }
                        dependency(HeightScreenDestination) { heightVm }
                        dependency(WeightScreenDestination) { weightVm }
                        dependency(ActivityScreenDestination) { activityLevelVm }
                        dependency(GoalScreenDestination) { goalVm }
                    }
                )
            }
        }
    }
}