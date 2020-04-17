package com.scott.mymm

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
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

    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView:ImageView,url:String?){
            Log.e("url===",""+url)
            Glide.with(imageView.context).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        }
    }


}