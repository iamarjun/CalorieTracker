package com.arjun.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.arjun.core_ui.LocalSpacing
import com.arjun.onboarding_presentation.R
import com.arjun.onboarding_presentation.components.ActionButton
import com.arjun.onboarding_presentation.destinations.GenderScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
    _modifier: Modifier,
    scaffoldState: ScaffoldState
) {
    Scaffold(modifier = _modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        val modifier = _modifier.padding(it)
        val spacing = LocalSpacing.current
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = modifier,
                text = stringResource(id = R.string.welcome_text),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )

            Spacer(modifier = modifier.height(spacing.spaceMedium))

            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = { navigator.navigate(GenderScreenDestination) },
                modifier = modifier.align(Alignment.CenterHorizontally),
                isEnabled = true
            )

        }
    }
}