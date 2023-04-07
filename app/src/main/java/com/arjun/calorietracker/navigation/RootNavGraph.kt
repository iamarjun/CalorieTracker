package com.arjun.calorietracker.navigation

import com.arjun.onboarding_presentation.OnboardingNavGraph
import com.arjun.tracker_presentation.TrackerNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object RootNavGraph : NavGraphSpec {

    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = OnboardingNavGraph

    override val nestedNavGraphs = listOf(
        OnboardingNavGraph,
        TrackerNavGraph
    )
}