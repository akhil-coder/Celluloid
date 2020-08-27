package com.example.celluloid;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celluloid.requests.responses.Genre;
import com.example.celluloid.util.Resource;
import com.example.celluloid.viewmodels.MovieDetailsViewModel;

import java.util.List;

public class GenreActivity extends BaseActivity {

    private static final String TAG = "GenreActivity";
    private List<Genre> genres;
    private RecyclerView recyclerView;
    MovieDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        recyclerView = findViewById(R.id.recycler_view_genre);
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        viewModel.getMovieGenre();
        viewModel.loadGenre().observe(this, new Observer<Resource<List<Genre>>>() {
            @Override
            public void onChanged(Resource<List<Genre>> listResource) {
                Log.d(TAG, "onChanged: Status " + listResource.status);
                if(listResource.data != null) {
                    for(Genre genre: listResource.data) {
                        Log.d(TAG, "onChanged: Data " + genre.getName());
                    }
                }
            } 
        });
    }
}