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

           val errorMessage = when(e.message){
                "sign_up_error"-> R.string.error_sign_up
                "sign_in_error"-> R.string.error_sign_in
                "user_already_exists" -> R.string.user_already_exists
                else -> R.string.error_desconocido
            }

            ApiResponseStatus.Error(errorMessage)
        }


}