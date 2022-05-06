package com.example.testassignment.utils;
import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $")

//        AppApplication.sessionManager.authDetails.accessToken?.let {
//        }
        return chain.proceed(requestBuilder.build())
    }
}