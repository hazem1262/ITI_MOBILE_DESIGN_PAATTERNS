package com.hossameldeenaltokhy.unitedtechnicalapp.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hossameldeenaltokhy.unitedtechnicalapp.R
import com.hossameldeenaltokhy.unitedtechnicalapp.base.BaseActivity
import com.hossameldeenaltokhy.unitedtechnicalapp.base.Resource
import com.hossameldeenaltokhy.unitedtechnicalapp.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : BaseActivity() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        attachUserListObserver()
    }

    private fun attachUserListObserver() {
        showHideLoading(true)
        userViewModel.getUsers().observe(this, Observer {
            if (it.status == Resource.Status.SUCCESS){
                showHideLoading(false)
                val adapter =
                    UsersListAdapter(
                        it.data!!
                    )
                usersRecyclerView.adapter = adapter
                // handle if there is no users [if first time app starts and there is no internet connection]
                if (it.data.isEmpty()){
                    noUsersTV.visibility = View.VISIBLE
                }
            } else{
                // handle if there is error response
                showErrorMessage(it.exception?.message?:"")
            }
        })
    }

    private fun showHideLoading(showLoading:Boolean){
        if (showLoading){
            loadingIndicator.visibility = View.VISIBLE
        }else {
            loadingIndicator.visibility = View.GONE
        }
    }

}

