package com.example.newshub.networkinglayer

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptorKey : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val request = originalUrl.newBuilder()
            .addQueryParameter("apiKey" ,"f59b1766d40c4075b99452ae966bb724")
            .build()

        val newRequest = original.newBuilder()
            .url(request)
            .build()

        return chain.proceed(newRequest)
    }
}