package com.scott.mymm

import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.scott.base.BaseActivity
import com.scott.mymm.databinding.ActivityMainBinding
import com.scott.simple.ImagesAdapter
import com.scott.utils.StringUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun initView() {
        val layoutParams = classHeader.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = StringUtils.getStatusBarHeight(this)

    }

    override val initVariableId: Int get() = BR.viewmodel
    override val rootViewId: Int get() = R.layout.activity_main

    override fun initData() {
        viewModel.getBanner()
        val imagesAdapter = ImagesAdapter()
        viewModel.name.observe(this, Observer {
            imagesAdapter.setNewData(it)
        })
    }

}
