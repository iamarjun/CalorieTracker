package com.arjun.tracker_presentation

import androidx.annotation.DrawableRes
import com.arjun.core.utils.UiEffect
import com.arjun.core.utils.UiEvent
import com.arjun.core.utils.UiState
import com.arjun.core.utils.UiText
import com.arjun.core_ui.R
import com.arjun.tracker_domain.model.MealType
import com.arjun.tracker_domain.model.TrackedFood
import java.time.LocalDate

class TrackerContract {

    sealed class Event : UiEvent {
        object OnNextDayClick : Event()
        object OnPreviousDayClick : Event()
        data class OnToggleMealClick(val meal: Meal) : Event()
        data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : Event()
        data class OnAddFoodClick(val meal: Meal) : Event()
    }

    data class Meal(
        val name: UiText,
        @DrawableRes val drawableRes: Int,
        val mealType: MealType,
        val carbs: Int = 0,
        val protein: Int = 0,
        val fat: Int = 0,
        val calories: Int = 0,
        val isExpanded: Boolean = false
    ) {

        companion object {
            val defaultMeals = listOf(
                Meal(
                    name = UiText.StringResource(R.string.breakfast),
                    drawableRes = R.drawable.ic_breakfast,
                    mealType = MealType.Breakfast
                ),
                Meal(
                    name = UiText.StringResource(R.string.lunch),
                    drawableRes = R.drawable.ic_lunch,
                    mealType = MealType.Lunch
                ),
                Meal(
                    name = UiText.StringResource(R.string.dinner),
                    drawableRes = R.drawable.ic_dinner,
                    mealType = MealType.Dinner
                ),
                Meal(
                    name = UiText.StringResource(R.string.snacks),
                    drawableRes = R.drawable.ic_snack,
                    mealType = MealType.Snack
                ),
            )
        }
    }

    data class State(
        val totalCarbs: Int = 0,
        val totalProtein: Int = 0,
        val totalFat: Int = 0,
        val totalCalories: Int = 0,
        val carbsGoal: Int = 0,
        val proteinGoal: Int = 0,
        val fatGoal: Int = 0,
        val caloriesGoal: Int = 0,
        val date: LocalDate = LocalDate.now(),
        val trackedFoods: List<TrackedFood> = emptyList(),
        val meals: List<Meal> = Meal.defaultMeals
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
        data class Navigate(val route: String) : Effect()
    }
}