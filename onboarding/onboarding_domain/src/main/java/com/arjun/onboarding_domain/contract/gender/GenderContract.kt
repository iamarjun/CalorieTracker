package com.arjun.onboarding_domain.contract.gender

import com.arjun.core.domain.model.Gender
import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState

class GenderContract {

    // Events that user performed
    sealed class Event : UiEvent {
        object OnNextClick : Event()
        data class OnGenderSelection(val gender: Gender): Event()
    }

    // Ui View States
    data class State(
        val gender: Gender = Gender.Male
    ) : UiState

    // View State that related to Gender

    // Side effects
    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }

}