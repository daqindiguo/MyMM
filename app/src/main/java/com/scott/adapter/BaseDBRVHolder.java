package com.scott.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public final class BaseDBRVHolder extends RecyclerView.ViewHolder {
    public int variableId;

    public BaseDBRVHolder(View itemView,int variableId) {
        super(itemView);
        this.variableId=variableId;

    }
}
