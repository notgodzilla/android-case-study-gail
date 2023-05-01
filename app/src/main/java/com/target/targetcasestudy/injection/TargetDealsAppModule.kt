package com.target.targetcasestudy.injection

import com.target.targetcasestudy.api.DealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TargetDealsAppModule {

    @Provides
    @Singleton
    fun provideDealsRepository(): DealsRepository = DealsRepository()
}