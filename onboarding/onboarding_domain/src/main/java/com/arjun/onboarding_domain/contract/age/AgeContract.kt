package com.arjun.onboarding_domain.contract.age

import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState

class AgeContract {

    // Events that user performed
    sealed class Event : UiEvent {
        data class OnAgeChange(val age: String) : Event()
    }

    // Ui View States
    data class State(
        val age: Int = 0
    ) : UiState

    // View State that related to Gender

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

}