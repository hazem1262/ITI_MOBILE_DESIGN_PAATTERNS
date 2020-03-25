package com.hossameldeenaltokhy.unitedtechnicalapp.data.api

import androidx.lifecycle.MutableLiveData
import com.hossameldeenaltokhy.unitedtechnicalapp.base.Resource
import com.hossameldeenaltokhy.unitedtechnicalapp.base.RetrofitClient
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class UsersApiProvider {

    fun getUsersList(): MutableLiveData<Resource<List<User>>> {
        val data = MutableLiveData<Resource<List<User>>>()
        api.getUserList().enqueue(
            object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    data.value = Resource.create(response)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val exception = Exception(t)
                    data.value = Resource.error(exception)
                }
            }
        )
        return data
    }
    interface UserInterface {
        @GET("users")
        fun getUserList(): Call<List<User>>
    }
    companion object {
        private val api : UserInterface by lazy { RetrofitClient.buildService(UserInterface::class.java) }
    }
}