package com.arjun.tracker_data.di

import android.content.Context
import com.arjun.tracker_data.local.db.TrackedFoodDb
import com.arjun.tracker_data.remote.api.OpenFoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    fun providesHttpInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun providesConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder().baseUrl(OpenFoodApi.BASE_URL).client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    fun providesOpenApi(retrofit: Retrofit): OpenFoodApi = retrofit.create(OpenFoodApi::class.java)

    @Provides
    fun providesTrackedFoodDb(@ApplicationContext context: Context): TrackedFoodDb =
        TrackedFoodDb.getInstance(context)

    @Provides
    fun providesTrackedFoodDao(trackedFoodDb: TrackedFoodDb) = trackedFoodDb.trackedFoodDao()
}