package com.arjun.core.utils.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Float,
    val goal: Goal,
    val activityLevel: ActivityLevel,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float,
)
