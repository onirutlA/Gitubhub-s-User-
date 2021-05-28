package com.onirutla.githubsuser.di

import com.onirutla.githubsuser.data.source.remote.network.GithubApiService
import com.onirutla.githubsuser.util.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideMoshiBuilder(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(provideMoshiBuilder()))
            .baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)
}

