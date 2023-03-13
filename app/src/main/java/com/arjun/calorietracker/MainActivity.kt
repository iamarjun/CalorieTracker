package com.arjun.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arjun.calorietracker.ui.theme.CalorieTrackerTheme
import com.arjun.onboarding_presentation.NavGraphs
import com.arjun.onboarding_presentation.age.AgeViewModel
import com.arjun.onboarding_presentation.destinations.AgeScreenDestination
import com.arjun.onboarding_presentation.destinations.GenderScreenDestination
import com.arjun.onboarding_presentation.gender.GenderViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val genderVm = viewModel<GenderViewModel>()
                val ageVm = viewModel<AgeViewModel>()
                val modifier = Modifier
                val scaffoldState = rememberScaffoldState()
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(modifier)
                        dependency(scaffoldState)
                        dependency(GenderScreenDestination) {
                            genderVm
                        }
                        dependency(AgeScreenDestination) {
                            ageVm
                        }
                    }
                )
            }
        }
    }
}