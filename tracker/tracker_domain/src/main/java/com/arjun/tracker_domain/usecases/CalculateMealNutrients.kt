package com.arjun.tracker_domain.usecases

import com.arjun.core.domain.model.ActivityLevel
import com.arjun.core.domain.model.Gender
import com.arjun.core.domain.model.Goal
import com.arjun.core.domain.model.UserInfo
import com.arjun.core.domain.preference.Preferences
import com.arjun.tracker_domain.model.MealType
import com.arjun.tracker_domain.model.TrackedFood
import javax.inject.Inject
import kotlin.math.roundToInt

class CalculateMealNutrients @Inject constructor(
    private val preferences: Preferences,
) {

    operator fun invoke(trackedFood: List<TrackedFood>): Result {
        val allNutrients = trackedFood.groupBy { it.mealType }
            .mapValues { entry ->
                val mealType = entry.key
                val food = entry.value
                MealNutrients(
                    protein = food.sumOf { it.protein },
                    carbs = food.sumOf { it.carbs },
                    fat = food.sumOf { it.fat },
                    calories = food.sumOf { it.calories },
                    mealType = mealType
                )
            }

        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProteins = allNutrients.values.sumOf { it.protein }
        val totalFats = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calorieGoal = dailyCaloriesRequirement(userInfo)
        val carbsGoal = (calorieGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (calorieGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (calorieGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            calorieGoal = calorieGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProteins,
            totalFat = totalFats,
            totalCalories = totalCalories,
            mealNutrient = allNutrients
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }

            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloriesRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val calorieExtra = when (userInfo.goal) {
            is Goal.LoseWeight -> -500
            is Goal.MaintainWeight -> 0
            is Goal.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + calorieExtra).roundToInt()
    }

    data class MealNutrients(
        val protein: Int,
        val carbs: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val calorieGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrient: Map<MealType, MealNutrients>
    )
}