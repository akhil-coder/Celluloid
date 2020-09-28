package com.example.celluloid;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.celluloid.adapter.MovieViewAdapter;
import com.example.celluloid.adapter.OnMovieClickListener;
import com.example.celluloid.requests.responses.movies.MovieDetails;
import com.example.celluloid.util.Resource;
import com.example.celluloid.util.VerticalSpacingItemDecorator;
import com.example.celluloid.viewmodels.MovieDetailsViewModel;

import java.util.List;

public class MovieActivity extends BaseActivity implements OnMovieClickListener {

    private static final String TAG = "MovieActivity";

    RecyclerView mRecyclerView;
    MovieViewAdapter mAdapter;
    MovieDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        mViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        getIncomingIntent();
        initRecyclerView();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("genre")) {
            int genreSelected = getIntent().getIntExtra("genre", 1);
            Log.d(TAG, "getIncomingIntent: " + genreSelected);
            mViewModel.getMovieList(genreSelected);
            subscribeObservers();
        }
    }

    private void subscribeObservers() {
        mViewModel.loadMovieList().observe(this, new Observer<Resource<List<MovieDetails>>>() {
            @Override
            public void onChanged(Resource<List<MovieDetails>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING: {
                            Log.d(TAG, "onChanged: Loading");
                            mAdapter.displayLoading();
                            break;
                        }
                        case ERROR: {
                            Log.e(TAG, "onChanged: Cannot refresh the cache");
                            Log.e(TAG, "onChanged: " + listResource.message);
                            mAdapter.setMovieList(listResource.data);                        }
                        case SUCCESS: {
                            Log.d(TAG, "onChanged: Success");
                            mAdapter.setMovieList(listResource.data);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        ViewPreloadSizeProvider<String> viewPreloadSizeProvider = new ViewPreloadSizeProvider<>();
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);

        mRecyclerView = findViewById(R.id.recyclerview_generic);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(itemDecorator);
        mAdapter = new MovieViewAdapter(this, initGlide(), viewPreloadSizeProvider);

        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<String>(Glide.with(this),
                mAdapter,
                viewPreloadSizeProvider,
                10);

        mRecyclerView.addOnScrollListener(preloader);
        mRecyclerView.setAdapter(mAdapter);
    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.whitebackground);
        return Glide.with(this).setDefaultRequestOptions(options);
    }

    @Override
    public void onMovieClick(int position) {

    }
}