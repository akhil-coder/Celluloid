package com.example.celluloid.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.celluloid.R;
import com.example.celluloid.requests.responses.movies.MovieDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ListPreloader.PreloadModelProvider<String> {

    private static final String TAG = "MovieViewAdapter";
    public static final int MOVIE_DETAILS = 1;
    public static final int LOADING_MOVIE = 2;

    private List<MovieDetails> mMovieDetails;
    private OnMovieClickListener onMovieClickListener;
    private RequestManager requestManager;
    private ViewPreloadSizeProvider<String> preloadSizeProvider;

    public MovieViewAdapter(OnMovieClickListener onMovieClickListener, RequestManager requestManager, ViewPreloadSizeProvider<String> preloadSizeProvider) {
        this.onMovieClickListener = onMovieClickListener;
        this.requestManager = requestManager;
        this.preloadSizeProvider = preloadSizeProvider;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case MOVIE_DETAILS: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
                return new MovieViewHolder(view, onMovieClickListener, requestManager, preloadSizeProvider);
            }

            case LOADING_MOVIE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_screen_view, parent, false);
                return new LoadingViewHolder(view);
            }

            default: {
                Log.d(TAG, "onCreateViewHolder: ");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_screen_view, parent, false);
                return new LoadingViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemView = getItemViewType(position);
        if (itemView == MOVIE_DETAILS) {
            ((MovieViewHolder) holder).onBind(mMovieDetails.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(mMovieDetails != null){
            return mMovieDetails.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMovieDetails.get(position).getTitle().equals("LOADING...")) {
            return LOADING_MOVIE;
        } else {
            return MOVIE_DETAILS;
        }
    }

    public void setMovieList(List<MovieDetails> details) {
        mMovieDetails = details;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {
        String url = mMovieDetails.get(position).getPoster_path();
        if (TextUtils.isEmpty(url))
            return Collections.emptyList();
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
        return requestManager.load(item);
    }

    public void displayLoading(){
        clearMovieList();
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setTitle("LOADING...");
        mMovieDetails.add(movieDetails);
        notifyDataSetChanged();
    }

    public void clearMovieList(){
        if(mMovieDetails == null){
            mMovieDetails = new ArrayList<>();
        } else {
            mMovieDetails.clear();
        }
        notifyDataSetChanged();
    }
}
