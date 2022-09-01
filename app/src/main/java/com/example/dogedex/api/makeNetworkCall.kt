package com.example.dogedex.api

import com.example.dogedex.R

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call : suspend () -> T
) : ApiResponseStatus<T>
    = withContext(Dispatchers.IO){
        try {
            ApiResponseStatus.Success(call())
        }catch (e: UnknownHostException){
            ApiResponseStatus.Error(R.string.error_internet)
        }catch (e: Exception){
            ApiResponseStatus.Error(R.string.error_desconocido)
        }


}