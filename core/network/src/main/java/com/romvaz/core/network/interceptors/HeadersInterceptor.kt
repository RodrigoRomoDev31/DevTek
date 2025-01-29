package com.romvaz.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                addHeader("accept", "application/json")
            }
            .build()
        return chain.proceed(request)
    }
}
