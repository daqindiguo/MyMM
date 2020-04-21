package com.scott.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseDBRVAdapter<Data> extends RecyclerView.Adapter<BaseDBRVHolder> {

    private List<Data> data;
    protected OnItemClickListener<Data> listener;

    public BaseDBRVAdapter() {
        data = new ArrayList<>();
    }

    public BaseDBRVAdapter(List<Data> data) {
        this.data = data == null ? new ArrayList<>() : data;
    }

    @NonNull
    @Override
    public BaseDBRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCVH(parent,viewType);
    }

    public abstract BaseDBRVHolder onCVH(@NonNull ViewGroup parent, int viewType);

    public ViewDataBinding bingLayout(ViewGroup parent,int layout){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return DataBindingUtil.inflate(inflater, layout, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRVHolder holder, final int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = data.get(position);
        if (binding != null) {
            binding.setVariable(holder.variableId, itemData);
            onBindViewHolder(itemData, binding, position);
            binding.executePendingBindings();
        }
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClick(itemData, position));
            holder.itemView.setOnLongClickListener(v -> listener.onItemLongClick(itemData, position));
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 绑定数据
     */
    protected void onBindViewHolder(Data data, ViewDataBinding binding, int position) {
    }

    /**
     * 设置新数据
     *
     * @param data
     */
    public void setNewData(List<Data> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(Data data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<Data> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 设置Item 长按、点击事件
     */
    public void setOnItemListener(OnItemClickListener<Data> listener) {
        this.listener = listener;
    }
}
