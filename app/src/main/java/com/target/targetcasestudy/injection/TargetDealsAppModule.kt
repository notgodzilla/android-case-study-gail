package com.target.targetcasestudy.injection

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.DealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TargetDealsAppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        //Create instance of Moshi for JSON converter
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        //Create instance of Retrofit for networking
        return Retrofit.Builder()
            .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideDealsAPI() = provideRetrofit().create<DealApiKtx>()

    @Provides
    @Singleton
    fun provideDealsRepository(): DealsRepository = DealsRepository(provideDealsAPI())
}