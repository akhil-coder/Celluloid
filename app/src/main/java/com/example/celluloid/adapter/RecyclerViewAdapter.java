package com.example.celluloid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.R;
import com.example.celluloid.requests.responses.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    public static final int GENRE_MOVIE_SELECT = 1;
    public static final int LOADING_TYPE = 5;


    private List<Genre> mGenre;
    private OnGenreListener mOnGenreListener;

    public RecyclerViewAdapter(OnGenreListener mOnGenreListener) {
        this.mOnGenreListener = mOnGenreListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case GENRE_MOVIE_SELECT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_item, parent, false);
                return new GenreViewHolder(view, mOnGenreListener);

            case LOADING_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_screen_view, parent, false);
                return new LoadingViewHolder(view);
            default:
                Log.d(TAG, "onCreateViewHolder: ");
                LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_screen_view, parent, false);
                return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemView = getItemViewType(position);
        if (itemView == GENRE_MOVIE_SELECT)
            ((GenreViewHolder) holder).tvGenre.setText(mGenre.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mGenre != null)
            return mGenre.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mGenre.get(position).getId() == -1) {
            return LOADING_TYPE;
        } else {
            return GENRE_MOVIE_SELECT;
        }
    }

    public void setGenre(List<Genre> genre) {
        mGenre = genre;
        notifyDataSetChanged();
    }

    public void setMovieList(List<Genre> genre) {
        mGenre = genre;
        notifyDataSetChanged();
    }

    public void displayLoading() {
        clearGenreList();
        Genre genre = new Genre();
        genre.setId(-1);
        mGenre.add(genre);
        notifyDataSetChanged();
    }

    private void clearGenreList() {
        if (mGenre == null) {
            mGenre = new ArrayList<>();
        } else {
            mGenre.clear();
        }
        notifyDataSetChanged();
    }
}