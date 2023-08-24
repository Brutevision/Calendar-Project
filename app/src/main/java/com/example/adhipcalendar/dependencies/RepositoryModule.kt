package com.example.adhipcalendar.dependencies

import com.example.adhipcalendar.data.ApiRepositoryImpl
import com.example.adhipcalendar.data.ApiRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMyRepository(repository: ApiRepositoryImpl): ApiRepositoryInterface {
        return repository
    }
}