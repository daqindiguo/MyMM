package com.scott.mymm

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scott.base.BaseActivity
import com.scott.mymm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun initView() {
        dataBinding.wrx = "7899"
    }

    override val initVariableId: Int get() = BR.viewmodel
    override val rootViewId: Int get() = R.layout.activity_main

    override fun initData() {
        viewModel.getBanner()
        viewModel.name.observe(this, Observer {

        })
    }



}
