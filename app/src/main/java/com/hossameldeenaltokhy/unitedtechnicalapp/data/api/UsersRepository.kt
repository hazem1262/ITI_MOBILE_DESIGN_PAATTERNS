package com.hossameldeenaltokhy.unitedtechnicalapp.data.api

import androidx.lifecycle.MutableLiveData
import com.hossameldeenaltokhy.unitedtechnicalapp.base.Resource
import com.hossameldeenaltokhy.unitedtechnicalapp.base.UnitedTechnicalApplication
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User


class UsersRepository {

    fun getUsersList():MutableLiveData<Resource<List<User>>>{
        return if (connectivityUtils?.isNetworkConnected != false){
            val usersFromNetwork = usersApi.getUsersList()
            // save users if request succeeded
            usersFromNetwork.observeForever {
                if (it?.status == Resource.Status.SUCCESS){
                    usersFromNetwork.value!!.data.let {
                        users -> db?.userDAO()?.saveUsers(users!!)
                    }
                    db?.close()
                }
            }
            usersFromNetwork
        }else {
            val data = MutableLiveData<Resource<List<User>>>()
            data.value = Resource.success(db?.userDAO()?.readUser())
            data
        }
    }
    private val usersApi by lazy { UsersApiProvider() }
    private val connectivityUtils by lazy { UnitedTechnicalApplication.connectivityUtils }
    private val db by lazy { UnitedTechnicalApplication.db }
}