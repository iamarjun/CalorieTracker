package com.arjun.tracker_presentation

import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val preferences: Preferences
) : BaseViewModel<TrackerContract.Event, TrackerContract.State, TrackerContract.Effect>() {

    init {
        preferences.saveShouldShowOnBoarding(false)
    }

    override fun createInitialState(): TrackerContract.State {
        return TrackerContract.State()
    }

    override fun handleEvent(event: TrackerContract.Event) {
        when (event) {
            is TrackerContract.Event.OnAddFoodClick -> setEffect {
                TrackerContract.Effect.Navigate(
                    route = ""
                )
            }
            is TrackerContract.Event.OnDeleteTrackedFoodClick -> TODO()
            TrackerContract.Event.OnNextDayClick -> TODO()
            TrackerContract.Event.OnPreviousDayClick -> TODO()
            is TrackerContract.Event.OnToggleMealClick -> TODO()
        }
    }
}