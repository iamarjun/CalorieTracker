package com.arjun.onboarding_presentation.height

import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.age.AgeContract
import com.arjun.onboarding_domain.contract.height.HeightContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<HeightContract.Event, HeightContract.State, HeightContract.Effect>() {

    override fun createInitialState(): HeightContract.State {
        return HeightContract.State()
    }

    override fun handleEvent(event: HeightContract.Event) {
        when (event) {
            is HeightContract.Event.OnHeightChange -> {
                if (event.height.isEmpty() || event.height.length >= 4) {
                    setEffect { HeightContract.Effect.ShowToast("Unrealistic height") }
                    return
                }

                val height = event.height.filter { it.isDigit() }.toInt()
                setState { copy(height = height) }
            }
        }
    }
}