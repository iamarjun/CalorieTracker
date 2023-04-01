package com.arjun.tracker_domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class TrackerUseCase @Inject constructor(
    private val trackFood: TrackFood,
    private val searchFood: SearchFood,
    private val getFoodForDate: GetFoodForDate,
    private val deleteFood: DeleteFood,
    private val calculateMealNutrients: CalculateMealNutrients
) {

}