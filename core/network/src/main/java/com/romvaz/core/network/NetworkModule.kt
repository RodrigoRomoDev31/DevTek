package com.romvaz.core.network

import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.network.interceptors.HeadersInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val timeOut: Long = 60

    // Provides a singleton instance of OkHttpClient with custom timeouts and an interceptor
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(HeadersInterceptor())
            .build()

    // Provides a singleton instance of Retrofit configured for the webHook API service
    @Provides
    @Singleton
    fun provideRetrofitForWebHook(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Provides a singleton instance of the API service WebHookApi
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WebHookApi =
        retrofit.create(WebHookApi::class.java)
}
