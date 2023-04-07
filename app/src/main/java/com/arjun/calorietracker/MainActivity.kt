package com.arjun.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.arjun.calorietracker.navigation.CoreFeatureNavigator
import com.arjun.calorietracker.navigation.RootNavGraph
import com.arjun.calorietracker.ui.theme.CalorieTrackerTheme
import com.arjun.core.domain.preference.Preferences
import com.arjun.onboarding_presentation.OnboardingNavGraph
import com.arjun.tracker_presentation.TrackerNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val modifier = Modifier
                val scaffoldState = rememberScaffoldState()
                DestinationsNavHost(
                    navController = navController,
                    navGraph = RootNavGraph,
                    startRoute = if (preferences.shouldShowOnBoarding()) OnboardingNavGraph else TrackerNavGraph,
                    dependenciesContainerBuilder = {
                        dependency(modifier)
                        dependency(scaffoldState)
                        dependency(CoreFeatureNavigator(destination, navController))
                    }
                )
            }
        }
    }
}