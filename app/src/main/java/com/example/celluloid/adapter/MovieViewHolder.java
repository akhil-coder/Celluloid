package com.example.celluloid.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.celluloid.R;
import com.example.celluloid.requests.responses.movies.MovieDetails;
import com.example.celluloid.util.Constants;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "MovieViewHolder";
    private TextView releaseDate;
    private TextView title;
    private AppCompatImageView imageView;
    OnMovieClickListener onMovieClickListener;
    ViewPreloadSizeProvider viewPreloadSizeProvider;
    RequestManager requestManager;


    public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener,
                           RequestManager requestManager,
                           ViewPreloadSizeProvider preloadSizeProvider) {
        super(itemView);
        this.onMovieClickListener = onMovieClickListener;
        this.viewPreloadSizeProvider = preloadSizeProvider;
        this.requestManager = requestManager;

        title = itemView.findViewById(R.id.tv_title);
        releaseDate = itemView.findViewById(R.id.tv_release_date);
        imageView = itemView.findViewById(R.id.iv_poster);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieClickListener.onMovieClick(getAdapterPosition());
    }

    public void onBind(MovieDetails movieDetails) {
        Log.d(TAG, "onBind: Image Url " + movieDetails.getPoster_path());
        requestManager.load(Constants.IMAGE_URL + movieDetails.getPoster_path()).into(imageView);
//      requestManager.load("https://image.tmdb.org/t/p/w92/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg").into(imageView);

        title.setText(movieDetails.getTitle());
        releaseDate.setText(String.valueOf(movieDetails.getPopularity()));
        viewPreloadSizeProvider.setView(imageView);
    }
}
