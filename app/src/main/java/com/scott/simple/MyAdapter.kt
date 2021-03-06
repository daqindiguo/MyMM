package com.scott.simple

import com.scott.adapter.DataBoundAdapter
import com.scott.adapter.DataBoundViewHolder
import com.scott.bean.BannerBean
import com.scott.mymm.R
import com.scott.mymm.databinding.ItemListBinding

/**
 *  time:2020/4/20
 **/
class MyAdapter:DataBoundAdapter<ItemListBinding>(R.layout.item_list) {
    private var bannerList= ArrayList<BannerBean>()

    override fun bindItem(
        holder: DataBoundViewHolder<ItemListBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
       holder.binding.banner=bannerList[position]
    }

    fun setNewData(list:List<BannerBean>){
        bannerList.clear()
        bannerList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

}