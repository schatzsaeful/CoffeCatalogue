package com.example.coffeecatalogue.network.response

sealed class ApiResult<out T : Any> {

    class Success<out T : Any>(val results: T) : ApiResult<T>()

    class Error(val exception: Throwable, ) : ApiResult<Nothing>() {

    }

}
