package com.scott.base

import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scott.mymm.App
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * time:2020/4/14
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    val showDialog by lazy { SingleLiveEvent<Void>() }
    val hideDialog by lazy { SingleLiveEvent<Void>() }

    fun <T> launchRequest(
        block: suspend CoroutineScope.() -> BaseResult<T>,
        success: (BaseResult<T>) -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            runCatching {
                if (isShowDialog) showLoading()
                withContext(Dispatchers.IO) { block() }
            }.onSuccess {
                hideLoading()
                success(it)
            }.onFailure {
                hideLoading()
                it.printStackTrace()
                httpException(it as Exception)
            }

        }
    }

    private fun httpException(e: Exception) {
        if (e is HttpException) {
            Toast.makeText(App.context, "服务器异常", Toast.LENGTH_SHORT).show()
        } else if (e is ConnectException || e is SocketTimeoutException) {
            Toast.makeText(App.context, "网络连接异常", Toast.LENGTH_SHORT).show()
        } else if (e is UnknownHostException) {
            Toast.makeText(App.context, "未知主机异常", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(App.context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading() {
        showDialog.value = null
    }

    private fun hideLoading() {
        hideDialog.value = null
    }


}