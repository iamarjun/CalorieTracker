package com.arjun.tracker_data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arjun.tracker_data.local.entity.TrackedFoodEntity

@Database(entities = [TrackedFoodEntity::class], version = 1)
abstract class TrackedFoodDb : RoomDatabase() {

    abstract fun trackedFoodDao(): TrackedFoodDao

    companion object {
        private var INSTANCE: TrackedFoodDb? = null
        private val lock = Any()
        fun getInstance(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: Room.databaseBuilder(context, TrackedFoodDb::class.java, "food_db").build()
                .also {
                    INSTANCE = it
                }
        }
    }
}