package com.scott.simple

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scott.base.BaseActivity
import com.scott.mymm.BR
import com.scott.mymm.MainViewModel
import com.scott.mymm.R
import com.scott.mymm.databinding.ActivityRecycleViewBinding
import kotlinx.android.synthetic.main.activity_recycle_view.*

class RecycleViewActivity : BaseActivity<MainViewModel, ActivityRecycleViewBinding>() {


    override val initVariableId: Int get() = BR.viewmodel
    override val rootViewId: Int get() = R.layout.activity_recycle_view
    private lateinit var myAdapter: MultAdapter

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        myAdapter = MultAdapter()
        recyclerview.adapter = myAdapter
    }

    override fun initData() {
        viewModel.getBanner()
        viewModel.name.observe(this, Observer {
            myAdapter.setBannerBeans(it)
        })
    }
}
