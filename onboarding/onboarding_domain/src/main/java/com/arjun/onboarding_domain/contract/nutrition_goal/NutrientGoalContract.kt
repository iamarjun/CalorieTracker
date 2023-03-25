package com.arjun.onboarding_domain.contract.nutrition_goal

import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState

class NutrientGoalContract {
    // Events that user performed
    sealed class Event : UiEvent {
        data class OnProteinRatioChange(val proteinRatio: String) : Event()
        data class OnCarbsRatioChange(val carbsRatio: String) : Event()
        data class OnFatsRatioChange(val fatsRatio: String) : Event()
    }

    // Ui View States
    data class State(
        val proteinRatio: Int = 40,
        val carbsRatio: Int = 30,
        val fatsRatio: Int = 30,
    ) : UiState

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}