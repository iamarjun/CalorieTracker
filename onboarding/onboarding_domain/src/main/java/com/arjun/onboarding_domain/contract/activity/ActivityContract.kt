package com.arjun.onboarding_domain.contract.activity

import com.arjun.core.domain.model.ActivityLevel
import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState

class ActivityContract {

    // Events that user performed
    sealed class Event : UiEvent {
        data class OnActivityChange(val activityLevel: ActivityLevel) : Event()
    }

    // Ui View States
    data class State(
        val activityLevel: ActivityLevel = ActivityLevel.Medium
    ) : UiState

    // View State that related to Gender

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

}