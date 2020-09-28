package com.example.celluloid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.R;
import com.example.celluloid.requests.responses.genre.Genre;

public class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvGenre;
    OnGenreListener onGenreListener;
    int mGenreId;
    public GenreViewHolder(@NonNull View itemView, OnGenreListener onGenreListener) {
        super(itemView);
        this.tvGenre = itemView.findViewById(R.id.tv_genre);
        this.onGenreListener = onGenreListener;
        itemView.setOnClickListener(this);
    }

    public void onBind(Genre genre) {
        tvGenre.setText(genre.getName());
        mGenreId = genre.getId();
    }

    @Override
    public void onClick(View v) {
        onGenreListener.onGenreClick(mGenreId);
    }
}
