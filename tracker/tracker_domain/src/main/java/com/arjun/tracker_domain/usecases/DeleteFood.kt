package com.arjun.tracker_domain.usecases

import com.arjun.tracker_domain.model.TrackedFood
import com.arjun.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class DeleteFood @Inject constructor(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}