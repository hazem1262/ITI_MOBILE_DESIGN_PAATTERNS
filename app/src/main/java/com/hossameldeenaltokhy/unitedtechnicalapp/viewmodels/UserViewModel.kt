package com.hossameldeenaltokhy.unitedtechnicalapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossameldeenaltokhy.unitedtechnicalapp.base.Resource
import com.hossameldeenaltokhy.unitedtechnicalapp.data.api.UsersRepository
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User

class UserViewModel: ViewModel() {

    fun getUsers():MutableLiveData<Resource<List<User>>>{
        return usersRepository.getUsersList()
    }
    private val usersRepository by lazy { UsersRepository() }

}