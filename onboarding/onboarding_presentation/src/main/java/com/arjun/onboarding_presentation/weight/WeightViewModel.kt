package com.arjun.onboarding_presentation.weight

import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.weight.WeightContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<WeightContract.Event, WeightContract.State, WeightContract.Effect>() {

    override fun createInitialState(): WeightContract.State {
        return WeightContract.State()
    }

    override fun handleEvent(event: WeightContract.Event) {
        when (event) {
            is WeightContract.Event.OnWeightChange -> {
                if (event.weight.isEmpty() || event.weight.length >= 3) {
                    setEffect { WeightContract.Effect.ShowToast("Unrealistic weight") }
                    return
                }

                val weight = event.weight.filter { it.isDigit() }.toInt()
                setState { copy(weight = weight) }
            }
        }
    }
}