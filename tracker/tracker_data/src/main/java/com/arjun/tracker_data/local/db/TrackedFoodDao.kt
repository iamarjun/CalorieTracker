package com.arjun.tracker_data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arjun.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TrackedFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTrackedFood(foodEntity: TrackedFoodEntity)

    @Delete
    abstract suspend fun deleteTrackedFood(foodEntity: TrackedFoodEntity)

    @Query("SELECT * FROM trackedfoodentity WHERE dayOfMonth = :day AND month = :month AND year = :year")
    abstract fun getFoodForTheDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>

}