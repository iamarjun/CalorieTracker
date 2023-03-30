package com.arjun.tracker_domain.usecases

import com.arjun.tracker_domain.model.TrackedFood
import com.arjun.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodForDate @Inject constructor(
    private val repository: TrackerRepository
) {
    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(localDate = date)
    }
}