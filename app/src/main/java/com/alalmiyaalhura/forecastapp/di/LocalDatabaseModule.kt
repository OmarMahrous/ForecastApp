package com.alalmiyaalhura.forecastapp.di

import android.app.Application
import androidx.room.Room
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDatabase
import com.alalmiyaalhura.forecastapp.data.source.local.database.type_converter.MyTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, ForecastDatabase::class.java, "forecast_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideForecastDao(database: ForecastDatabase) = database.forecastDao()

    @Provides
    fun provideTypeConverter() = MyTypeConverter()

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope