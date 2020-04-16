package com.scott.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.scott.base.BaseViewModel
import com.scott.mymm.App


class LoginViewModel : BaseViewModel() {
    var mobile = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var success = MutableLiveData<String>()

    private fun login(){
        when {
            mobile.value.isNullOrEmpty() -> {
                Toast.makeText(App.context,"mobile is not null",Toast.LENGTH_SHORT).show()
            }
            password.value.isNullOrEmpty() -> {
                Toast.makeText(App.context,"password is not null",Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(App.context,"login success",Toast.LENGTH_SHORT).show()
                success.value="success"
            }
        }
    }

    fun onViewClick(){
        login()
    }

}