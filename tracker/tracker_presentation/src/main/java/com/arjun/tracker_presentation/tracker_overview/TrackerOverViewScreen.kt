package com.arjun.tracker_presentation.tracker_overview

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TrackerOverViewScreen(
    navigator: TrackerOverviewScreenNavigator,
    _modifier: Modifier,
    scaffoldState: ScaffoldState,
    viewModel: TrackerViewModel = hiltViewModel()
) {

}