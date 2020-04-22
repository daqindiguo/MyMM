package com.scott.simple

import com.scott.adapter.DataBoundAdapter
import com.scott.adapter.DataBoundViewHolder
import com.scott.bean.BannerBean
import com.scott.mymm.R
import com.scott.mymm.databinding.ItemListBinding
import com.scott.mymm.databinding.ItmeImagesBinding

/**
 *  time:2020/4/20
 **/
class ImagesAdapter:DataBoundAdapter<ItmeImagesBinding>(R.layout.itme_images) {
    private var bannerList= ArrayList<BannerBean>()

    override fun bindItem(
        holder: DataBoundViewHolder<ItmeImagesBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
       holder.binding.mImage=bannerList[position]
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