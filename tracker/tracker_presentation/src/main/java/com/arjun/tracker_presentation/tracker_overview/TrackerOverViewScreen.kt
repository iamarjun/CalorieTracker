package com.arjun.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arjun.core_ui.LocalSpacing
import com.arjun.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun TrackerOverViewScreen(
    navigator: TrackerOverviewScreenNavigator,
    _modifier: Modifier,
    scaffoldState: ScaffoldState,
    viewModel: TrackerViewModel = hiltViewModel()
) {

    Scaffold(modifier = _modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        val modifier = _modifier.padding(it)
        val spacing = LocalSpacing.current

        val state by viewModel.uiState.collectAsState()

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                NutrientsHeader(modifier = modifier, state = state)
            }
        }
    }
}