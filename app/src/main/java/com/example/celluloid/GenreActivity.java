package com.example.celluloid;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.adapter.OnGenreListener;
import com.example.celluloid.adapter.RecyclerViewAdapter;
import com.example.celluloid.requests.responses.genre.Genre;
import com.example.celluloid.util.Resource;
import com.example.celluloid.util.VerticalSpacingItemDecorator;
import com.example.celluloid.viewmodels.MovieDetailsViewModel;

import java.util.List;

public class GenreActivity extends BaseActivity implements OnGenreListener {

    private static final String TAG = "GenreActivity";
    private List<Genre> genres;
    private RecyclerView mRecyclerView;
    MovieDetailsViewModel viewModel;
    RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        mRecyclerView = findViewById(R.id.recyclerview_generic);
        initRecyclerView();
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        viewModel.getMovieGenre();
        viewModel.loadGenre().observe(this, new Observer<Resource<List<Genre>>>() {
            @Override
            public void onChanged(Resource<List<Genre>> listResource) {
                if(listResource != null) {
                    if(listResource.data != null){
                        switch (listResource.status){
                            case LOADING:{
                                mAdapter.displayLoading();
                                break;
                            }
                            case ERROR:{

                            }
                            case SUCCESS:{
                                mAdapter.setGenre(listResource.data);
                                break;
                            }
                        }
                    }
                }
            } 
        });
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.displayLoading();
    }

    @Override
    public void onGenreClick(String genre) {

    }
}