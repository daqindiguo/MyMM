package com.scott.mymm

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scott.base.BaseActivity
import com.scott.mymm.databinding.ActivityMainBinding
import com.scott.simple.ImagesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun initView() {
    }

    override val initVariableId: Int get() = BR.viewmodel
    override val rootViewId: Int get() = R.layout.activity_main

    override fun initData() {
        viewModel.getBanner()
        val imagesAdapter = ImagesAdapter()
        viewPage2.adapter=imagesAdapter
        viewModel.name.observe(this, Observer {
            imagesAdapter.setNewData(it)
        })
    }

}
