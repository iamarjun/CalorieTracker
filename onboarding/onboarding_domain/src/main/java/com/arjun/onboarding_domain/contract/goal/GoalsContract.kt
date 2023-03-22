package com.arjun.onboarding_domain.contract.goal

import com.arjun.core.domain.model.Goal
import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState

class GoalsContract {

    // Events that user performed
    sealed class Event : UiEvent {
        data class OnGoalChange(val goal: Goal) : Event()
    }

    // Ui View States
    data class State(
        val goal: Goal = Goal.MaintainWeight
    ) : UiState

    // View State that related to Gender

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

}