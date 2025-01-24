package com.mahfuz.retrofit.utils

import com.mahfuz.retrofit.data.model.Post

sealed class ApiState{

    class Success(val data:List<Post> ) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()

}