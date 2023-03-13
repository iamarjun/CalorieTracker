package com.arjun.onboarding_presentation.age

import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.age.AgeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<AgeContract.Event, AgeContract.State, AgeContract.Effect>() {

    override fun createInitialState(): AgeContract.State {
        return AgeContract.State()
    }

    override fun handleEvent(event: AgeContract.Event) {
        when (event) {
            is AgeContract.Event.OnAgeChange -> {
                if (event.age.isEmpty() || event.age.length >= 3) {
                    setEffect { AgeContract.Effect.ShowToast("Unrealistic age") }
                    return
                }

                val age = event.age.filter { it.isDigit() }.toInt()
                setState { copy(age = age) }
            }
        }
    }
}