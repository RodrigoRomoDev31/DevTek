package com.romvaz.core.network

import android.content.Context
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.network.connectivity.InternetStatusInterface
import com.romvaz.core.network.connectivity.InternetStatusInterfaceService
import com.romvaz.core.network.interceptors.HeadersInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(HeadersInterceptor())
            .build()


    @Provides
    @Singleton
    fun provideRetrofitForMovies(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WebHookApi =
        retrofit.create(WebHookApi::class.java)

    @Provides
    @Singleton
    fun providesInternetStatus(@ApplicationContext context: Context): InternetStatusInterface =
        InternetStatusInterfaceService(context)
}
