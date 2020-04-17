package com.scott.ext

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scott.base.BaseViewModel
import com.scott.utils.DensityUtil
import com.scott.utils.MaterialCircleImageView
import java.lang.reflect.ParameterizedType

/**
 * 获取vm clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

fun <VM : BaseViewModel> AppCompatActivity.getViewModel():VM{
    return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
}





