package com.scott.simple

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.scott.adapter.BaseDBRVAdapter
import com.scott.adapter.BaseDBRVHolder
import com.scott.bean.BannerBean
import com.scott.mymm.BR
import com.scott.mymm.R

/**
 *  time:2020/4/20
 **/
class MyAdapter : BaseDBRVAdapter<BannerBean>() {

    override fun getItemViewType(position: Int): Int {
        return if (position==0){
            0
        }else
            1
    }

    override fun onCVH(parent: ViewGroup, viewType: Int): BaseDBRVHolder {
        Log.e("viewType===",viewType.toString())
        return if (viewType==0){
            BaseDBRVHolder(bingLayout(parent,R.layout.itme_images).root,BR.mImage)
        }else{
            BaseDBRVHolder(bingLayout(parent,R.layout.item_list).root,BR.xxoo)
        }
    }

}