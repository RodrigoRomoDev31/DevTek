package com.romvaz.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for HTTP requests
 * This interceptor MOST only contain general headers
 * Add needed headers inside apply
 */
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
