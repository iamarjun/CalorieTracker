package com.arjun.core.domain.usecase

import javax.inject.Inject

class ValidateNutrition @Inject constructor() {

    operator fun invoke(proteinRatio: Int, carbsRatio: Int, fatsRatio: Int): Boolean {
        if (proteinRatio < 0 || carbsRatio < 0 || fatsRatio < 0)
            return false
        if (carbsRatio + proteinRatio + fatsRatio > 100)
            return false

        return true
    }
}