package com.arjun.onboarding_presentation.gender

import androidx.lifecycle.viewModelScope
import com.arjun.core.domain.model.Gender
import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.gender.GenderContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<GenderContract.Event, GenderContract.State, GenderContract.Effect>() {

    override fun createInitialState(): GenderContract.State {
        return GenderContract.State()
    }

    override fun handleEvent(event: GenderContract.Event) {
        when (event) {
            is GenderContract.Event.OnGenderSelection -> {
                saveGender(event.gender)
            }
        }
    }

    private fun saveGender(gender: Gender) {
        viewModelScope.launch {
            setState { copy(gender = gender) }
            preferences.saveGender(gender)
        }
    }


}