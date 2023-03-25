package com.arjun.onboarding_presentation.nutrition_goal

import com.arjun.core.domain.preference.Preferences
import com.arjun.core.domain.usecase.FilterDigits
import com.arjun.core.domain.usecase.ValidateNutrition
import com.arjun.core.utils.BaseViewModel
import com.arjun.onboarding_domain.contract.nutrition_goal.NutrientGoalContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigits: FilterDigits,
    private val validateNutrition: ValidateNutrition
) : BaseViewModel<NutrientGoalContract.Event, NutrientGoalContract.State, NutrientGoalContract.Effect>() {

    override fun createInitialState(): NutrientGoalContract.State {
        return NutrientGoalContract.State()
    }

    override fun handleEvent(event: NutrientGoalContract.Event) {
        when (event) {
            is NutrientGoalContract.Event.OnCarbsRatioChange -> {
                val carbsRatioChange = filterDigits(event.carbsRatio)
                val isValidRatio = validateNutrition(
                    uiState.value.proteinRatio,
                    carbsRatioChange,
                    uiState.value.fatsRatio
                )
                if (isValidRatio)
                    setState { copy(carbsRatio = carbsRatioChange) }
                else
                    setEffect {
                        NutrientGoalContract.Effect.ShowToast("Ratio cannot exceed 100")
                    }
            }

            is NutrientGoalContract.Event.OnFatsRatioChange -> {
                val fatsRatio = filterDigits(event.fatsRatio)

                val isValidRatio = validateNutrition(
                    uiState.value.proteinRatio,
                    uiState.value.carbsRatio,
                    fatsRatio
                )
                if (isValidRatio)
                    setState { copy(fatsRatio = fatsRatio) }
                else
                    setEffect {
                        NutrientGoalContract.Effect.ShowToast("Ratio cannot exceed 100")
                    }

            }

            is NutrientGoalContract.Event.OnProteinRatioChange -> {
                val proteinRatio = filterDigits(event.proteinRatio)

                val isValidRatio = validateNutrition(
                    filterDigits(event.proteinRatio),
                    uiState.value.carbsRatio,
                    uiState.value.fatsRatio
                )

                if (isValidRatio)
                    setState { copy(proteinRatio = proteinRatio) }
                else
                    setEffect {
                        NutrientGoalContract.Effect.ShowToast("Ratio cannot exceed 100")
                    }
            }
        }
    }
}