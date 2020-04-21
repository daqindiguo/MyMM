package com.scott.adapter;


public interface OnItemClickListener<Data> {

    void onItemClick(Data data, int position);

    boolean onItemLongClick(Data data, int position);

}
