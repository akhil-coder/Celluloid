package com.example.celluloid.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewGenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int GENRE_MOVIE_SELECT = 1;
    public static final int GENRE_TV_SELECT = 2;
    public static final int MOVIE_LIST = 3;
    public static final int TV_LIST = 4;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
