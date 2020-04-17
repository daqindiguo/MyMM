package com.scott.mymm

import androidx.lifecycle.Observer
import com.scott.base.BaseActivity
import com.scott.mymm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun initView() {
    }

    override val initVariableId: Int get() = BR.viewmodel
    override val rootViewId: Int get() = R.layout.activity_main

    override fun initData() {
        viewModel.getBanner()
        viewModel.name.observe(this, Observer {
        })
    }

}
