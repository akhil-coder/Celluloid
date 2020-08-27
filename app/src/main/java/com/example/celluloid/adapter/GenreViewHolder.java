package com.example.celluloid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.R;
import com.example.celluloid.requests.responses.Genre;

public class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvGenre;
    OnGenreListener onGenreListener;

    public GenreViewHolder(@NonNull View itemView, TextView tvGenre, OnGenreListener onGenreListener) {
        super(itemView);
        this.tvGenre = itemView.findViewById(R.id.tv_genre_name);
        this.onGenreListener = onGenreListener;
        itemView.setOnClickListener(this);
    }

    public void onBind(Genre genre) {
        tvGenre.setText(genre.getName());
    }

    @Override
    public void onClick(View v) {
        onGenreListener.onGenreClick(tvGenre.getText().toString());
    }
}
