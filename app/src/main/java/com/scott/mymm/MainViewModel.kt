package com.scott.mymm

import androidx.lifecycle.MutableLiveData
import com.scott.base.BaseViewModel
import com.scott.bean.BannerBean
import com.scott.http.HomeNetWork

/**
 * time:2020/4/14
 */
class MainViewModel : BaseViewModel() {
    var name = MutableLiveData<List<BannerBean>>()

    fun getBanner() {
        launchRequest(block = {
            HomeNetWork.getInstance().getBannerData()
        }, success = {
            name.value = it.data
        })
    }
}