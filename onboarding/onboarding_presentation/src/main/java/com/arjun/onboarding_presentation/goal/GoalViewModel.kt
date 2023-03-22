package com.arjun.onboarding_presentation.goal

import androidx.lifecycle.viewModelScope
import com.arjun.core.domain.model.Goal
import com.arjun.core.domain.preference.Preferences
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.goal.GoalsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val preferences: Preferences) :
    BaseViewModel<GoalsContract.Event, GoalsContract.State, GoalsContract.Effect>() {

    override fun createInitialState(): GoalsContract.State {
        return GoalsContract.State()
    }

    override fun handleEvent(event: GoalsContract.Event) {
        when (event) {
            is GoalsContract.Event.OnGoalChange -> {
                saveGender(event.goal)
            }

            else -> {}
        }
    }

    private fun saveGender(goal: Goal) {
        viewModelScope.launch {
            setState { copy(goal = goal) }
            preferences.saveGoal(goal)
        }
    }


}