package com.scott.simple

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scott.adapter.BaseDataBoundAdapter
import com.scott.adapter.DataBoundViewHolder
import com.scott.bean.BannerBean
import com.scott.mymm.R
import kotlinx.android.synthetic.main.recyclerview1.view.*
import java.util.*

/**
 * time:2020/4/22
 */
class MultAdapter : BaseDataBoundAdapter<ViewDataBinding>() {
    private  val txtAdapter:MyAdapter by lazy { MyAdapter() }
    private  val imgAdapter:ImagesAdapter by lazy { ImagesAdapter() }

    private val bannerBeans: MutableList<BannerBean> =
        ArrayList()

    fun setBannerBeans(list: List<BannerBean>?) {
        bannerBeans.clear()
        bannerBeans.addAll(list!!)
        txtAdapter.setNewData(list)
        imgAdapter.setNewData(list)
        notifyDataSetChanged()
    }

    override fun getItemLayoutId(position: Int): Int {
        return if (position == 0) {
            R.layout.recyclerview1
        } else {
            R.layout.recyclerview1
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun bindItem(
        holder: DataBoundViewHolder<ViewDataBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (position== 0){
            holder.itemView.text_recyclerview.layoutManager=
                LinearLayoutManager(holder.itemView.context) as RecyclerView.LayoutManager?
            holder.itemView.text_recyclerview.adapter=txtAdapter
        }else{
            holder.itemView.text_recyclerview.layoutManager=
                LinearLayoutManager(holder.itemView.context) as RecyclerView.LayoutManager?
            holder.itemView.text_recyclerview.adapter=imgAdapter
        }
    }
}