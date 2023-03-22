package com.arjun.onboarding_presentation.activity

import androidx.lifecycle.viewModelScope
import com.arjun.core.domain.model.ActivityLevel
import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.activity.ActivityContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<ActivityContract.Event, ActivityContract.State, ActivityContract.Effect>() {

    override fun createInitialState(): ActivityContract.State {
        return ActivityContract.State()
    }

    override fun handleEvent(event: ActivityContract.Event) {
        when (event) {
            is ActivityContract.Event.OnActivityChange -> {
                saveGender(event.activityLevel)
            }
        }
    }

    private fun saveGender(activityLevel: ActivityLevel) {
        viewModelScope.launch {
            setState { copy(activityLevel = activityLevel) }
            preferences.saveActivityLevel(activityLevel)
        }
    }


}