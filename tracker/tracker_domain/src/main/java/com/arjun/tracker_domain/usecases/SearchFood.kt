package com.arjun.tracker_domain.usecases

import com.arjun.tracker_domain.model.TrackableFood
import com.arjun.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class SearchFood @Inject constructor(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 50
    ): Result<List<TrackableFood>> {
        if (query.isBlank())
            return Result.success(emptyList())

        return repository.searchFood(query, page, pageSize)
    }
}