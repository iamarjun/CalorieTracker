package com.arjun.tracker_presentation.tracker_overview

interface TrackerOverviewScreenNavigator {
    fun navigateToSearch(mealName: String, dayOfMonth: Int, month: Int, year: Int)
}