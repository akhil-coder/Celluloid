package com.example.celluloid.adapter;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {
    ProgressBar mProgressbar;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        mProgressbar = itemView.findViewById(R.id.progressbar_recycler);
    }
}
