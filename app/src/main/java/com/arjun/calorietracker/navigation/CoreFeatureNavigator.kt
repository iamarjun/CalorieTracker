package com.arjun.calorietracker.navigation

import androidx.navigation.NavController
import com.arjun.onboarding_presentation.OnboardingNavigator
import com.arjun.onboarding_presentation.destinations.ActivityScreenDestination
import com.arjun.onboarding_presentation.destinations.AgeScreenDestination
import com.arjun.onboarding_presentation.destinations.GenderScreenDestination
import com.arjun.onboarding_presentation.destinations.GoalScreenDestination
import com.arjun.onboarding_presentation.destinations.HeightScreenDestination
import com.arjun.onboarding_presentation.destinations.NutrientGoalScreenDestination
import com.arjun.onboarding_presentation.destinations.OnboardingDestination
import com.arjun.onboarding_presentation.destinations.WeightScreenDestination
import com.arjun.onboarding_presentation.destinations.WelcomeScreenDestination
import com.arjun.tracker_presentation.destinations.SearchScreenDestination
import com.arjun.tracker_presentation.destinations.TrackerOverViewScreenDestination
import com.arjun.tracker_presentation.search.SearchScreenNavigator
import com.arjun.tracker_presentation.tracker_overview.TrackerOverviewScreenNavigator
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationSpec

class CoreFeatureNavigator(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : SearchScreenNavigator, TrackerOverviewScreenNavigator, OnboardingNavigator {

    override fun navigateToNextScreen() {
        currentDestination as? OnboardingDestination
            ?: throw RuntimeException("Trying to use Onboarding navigator from a non onboarding screen")
        val nextDirection = when (currentDestination) {
            WelcomeScreenDestination -> GenderScreenDestination
            GenderScreenDestination -> AgeScreenDestination
            AgeScreenDestination -> HeightScreenDestination
            HeightScreenDestination -> WeightScreenDestination
            WeightScreenDestination -> ActivityScreenDestination
            ActivityScreenDestination -> GoalScreenDestination
            GoalScreenDestination -> NutrientGoalScreenDestination
            NutrientGoalScreenDestination -> TrackerOverViewScreenDestination
        }

        navController.navigate(nextDirection)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToSearch(mealName: String, dayOfMonth: Int, month: Int, year: Int) {
        navController.navigate(SearchScreenDestination(mealName, dayOfMonth, month, year))
    }

}