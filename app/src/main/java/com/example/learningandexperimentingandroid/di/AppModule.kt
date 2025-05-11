package com.example.learningandexperimentingandroid.di

import com.example.learningandexperimentingandroid.dispatchersinjection.DispatcherProvider
import com.example.learningandexperimentingandroid.dispatchersinjection.DispatcherProviderImpl
import com.example.learningandexperimentingandroid.utils.logger.AppLogger
import com.example.learningandexperimentingandroid.utils.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

}