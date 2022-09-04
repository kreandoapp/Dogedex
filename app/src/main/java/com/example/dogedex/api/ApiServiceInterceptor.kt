package com.example.dogedex.api

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.RuntimeException

object ApiServiceInterceptor : Interceptor {

    const val NEED_AUTH_HEADER_KEY = "needs_authentication"

    private var sessionToken : String? = null

    fun setSesionToken(sessionToken : String){
        this.sessionToken = sessionToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if(request.header(NEED_AUTH_HEADER_KEY) != null){
            //need credentials
            if(sessionToken == null){
                throw  RuntimeException("Need to be autenticated")
            }else{
                requestBuilder.addHeader("AUTH-TOKEN",sessionToken!!)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}