package com.arjun.tracker_data.repository

import com.arjun.tracker_data.local.db.TrackedFoodDao
import com.arjun.tracker_data.mapper.toTrackableFood
import com.arjun.tracker_data.mapper.toTrackedFood
import com.arjun.tracker_data.mapper.toTrackedFoodEntity
import com.arjun.tracker_data.remote.api.OpenFoodApi
import com.arjun.tracker_domain.model.TrackableFood
import com.arjun.tracker_domain.model.TrackedFood
import com.arjun.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImp @Inject constructor(
    private val openFoodApi: OpenFoodApi,
    private val trackedFoodDao: TrackedFoodDao
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = openFoodApi.searchFood(query, page, pageSize)
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackedFoodDao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackedFoodDao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackedFoodDao.getFoodForTheDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map { it.map { it.toTrackedFood() } }
    }
}