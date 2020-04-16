package com.scott.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scott.utils.DensityUtil
import com.scott.utils.MaterialCircleImageView
import java.lang.reflect.ParameterizedType

/**
 * time:2020/4/14
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var dataBinding: DB
    private var loadingView: MaterialCircleImageView? = null
    private var loadingProgressView: CircularProgressDrawable? = null

    abstract val initVariableId:Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding()
        initLoading()
        initView()
        initData()
    }

    private fun initViewDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, rootViewId()) as DB
        viewModel = createViewModel()
        dataBinding.setVariable(initVariableId, viewModel)
        dataBinding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)

        viewModel.showDialog.observe(this, Observer { showLoading() })
        viewModel.hideDialog.observe(this, Observer { hideLoading() })
    }

    private fun initLoading() {
        loadingView = MaterialCircleImageView(this, -0x50506)
        loadingProgressView = CircularProgressDrawable(this)
        loadingProgressView?.setStyle(CircularProgressDrawable.DEFAULT)
        loadingProgressView?.setColorSchemeColors(-0xff6634, -0xbbbc, -0x996700, -0x559934, -0x7800)
        loadingView?.setImageDrawable(loadingProgressView)
        val lp: FrameLayout.LayoutParams =
            FrameLayout.LayoutParams(DensityUtil.dp2px(this, 40f), DensityUtil.dp2px(this, 40f))
        lp.gravity = Gravity.CENTER
        loadingView?.layoutParams = lp
        (window.findViewById(android.R.id.content) as ViewGroup).addView(loadingView)
        hideLoading()
    }

    private fun showLoading() {
        loadingProgressView?.start()
        loadingView?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loadingProgressView?.stop()
        loadingView?.visibility = View.GONE
    }

    abstract fun rootViewId(): Int
    abstract fun initView()
    abstract fun initData()


    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
    }

    @Suppress("UNCHECKED_CAST")
    fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    fun startActivity(cls:Class<*>){
        val intent = Intent(this, cls)
        startActivity(intent)
    }

}