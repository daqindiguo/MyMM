package com.scott.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scott.ext.getVmClazz

/**
 *  time:2020/4/17
 **/
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    lateinit var viewModel: VM
    lateinit var dataBinding: DB

    private lateinit var activity: BaseActivity<VM, DB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity() as BaseActivity<VM, DB>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, rootViewId, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        dataBinding.setVariable(initVariableId, viewModel)
        dataBinding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        viewModel.showDialog.observe(viewLifecycleOwner, Observer { showLoading() })
        viewModel.hideDialog.observe(viewLifecycleOwner, Observer { hideLoading() })
        initView()
        initData()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
    }

    private fun showLoading() {
        activity.showLoading()
    }

    private fun hideLoading() {
        activity.hideLoading()
    }

    abstract val rootViewId: Int
    abstract val initVariableId: Int
    abstract fun initView()
    abstract fun initData()
}