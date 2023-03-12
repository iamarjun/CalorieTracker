package com.arjun.core.utils.domain.preference

import com.arjun.core.utils.domain.model.ActivityLevel
import com.arjun.core.utils.domain.model.Gender
import com.arjun.core.utils.domain.model.Goal
import com.arjun.core.utils.domain.model.UserInfo

interface Preferences {

    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Float)
    fun saveGoal(goal: Goal)
    fun saveActivityLevel(activityLevel: ActivityLevel)
    fun saveCarbRatio(carbRatio: Float)
    fun saveProteinRatio(proteinRatio: Float)
    fun saveFatRatio(fatRatio: Float)

    fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_GOAL = "goal"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}