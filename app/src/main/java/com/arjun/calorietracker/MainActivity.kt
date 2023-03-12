package com.arjun.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arjun.calorietracker.ui.theme.CalorieTrackerTheme
import com.arjun.onboarding_presentation.NavGraphs
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
                val viewModel = viewModel<GenderViewModel>()
                val modifier = Modifier
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(modifier)
                        dependency(GenderScreenDestination) {
                            viewModel
                        }
                    }
                )
            }
        }
    }
}