package com.arjun.core.domain

import android.content.SharedPreferences
import com.arjun.core.domain.model.ActivityLevel
import com.arjun.core.domain.model.Gender
import com.arjun.core.domain.model.Goal
import com.arjun.core.domain.model.UserInfo
import com.arjun.core.domain.preference.Preferences
import com.arjun.core.domain.preference.Preferences.Companion.KEY_ACTIVITY_LEVEL
import com.arjun.core.domain.preference.Preferences.Companion.KEY_AGE
import com.arjun.core.domain.preference.Preferences.Companion.KEY_CARB_RATIO
import com.arjun.core.domain.preference.Preferences.Companion.KEY_FAT_RATIO
import com.arjun.core.domain.preference.Preferences.Companion.KEY_GENDER
import com.arjun.core.domain.preference.Preferences.Companion.KEY_GOAL
import com.arjun.core.domain.preference.Preferences.Companion.KEY_HEIGHT
import com.arjun.core.domain.preference.Preferences.Companion.KEY_PROTEIN_RATIO
import com.arjun.core.domain.preference.Preferences.Companion.KEY_WEIGHT
import javax.inject.Inject

class DefaultPreferences @Inject constructor(private val sharedPreference: SharedPreferences) :
    Preferences {
    override fun saveGender(gender: Gender) {
        sharedPreference.edit().putString(KEY_GENDER, gender.name).apply()
    }

    override fun saveAge(age: Int) {
        sharedPreference.edit().putInt(KEY_AGE, age).apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreference.edit().putFloat(KEY_WEIGHT, weight).apply()
    }

    override fun saveHeight(height: Float) {
        sharedPreference.edit().putFloat(KEY_HEIGHT, height).apply()
    }

    override fun saveGoal(goal: Goal) {
        sharedPreference.edit().putString(KEY_GOAL, goal.name).apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPreference.edit().putString(KEY_ACTIVITY_LEVEL, activityLevel.name).apply()
    }

    override fun saveCarbRatio(carbRatio: Float) {
        sharedPreference.edit().putFloat(KEY_CARB_RATIO, carbRatio).apply()
    }

    override fun saveProteinRatio(proteinRatio: Float) {
        sharedPreference.edit().putFloat(KEY_PROTEIN_RATIO, proteinRatio).apply()
    }

    override fun saveFatRatio(fatRatio: Float) {
        sharedPreference.edit().putFloat(KEY_FAT_RATIO, fatRatio).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val gender = sharedPreference.getString(KEY_GENDER, "")
        val age = sharedPreference.getInt(KEY_AGE, -1)
        val weight = sharedPreference.getFloat(KEY_WEIGHT, -1f)
        val height = sharedPreference.getFloat(KEY_HEIGHT, -1f)
        val goal = sharedPreference.getString(KEY_GOAL, "")
        val activityLevel = sharedPreference.getString(KEY_ACTIVITY_LEVEL, "")
        val carbRatio = sharedPreference.getFloat(KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPreference.getFloat(KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPreference.getFloat(KEY_FAT_RATIO, -1f)

        return UserInfo(
            Gender.fromString(gender),
            age,
            weight,
            height,
            Goal.fromString(goal),
            ActivityLevel.fromString(activityLevel),
            carbRatio,
            proteinRatio,
            fatRatio,
        )
    }
}