package com.example.celluloid.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.celluloid.repositories.TmDbRepository;
import com.example.celluloid.requests.responses.genre.Genre;
import com.example.celluloid.requests.responses.movies.MovieDetails;
import com.example.celluloid.util.Resource;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {
    private static final String TAG = "MovieDetailsViewModel";
    private MediatorLiveData<Resource<List<Genre>>> genre = new MediatorLiveData<>();
    private MediatorLiveData<Resource<List<MovieDetails>>> movieDetails = new MediatorLiveData<>();
    private TmDbRepository repository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = TmDbRepository.getInstance(application);
        init();
    }

    private void init() {
    }

    public LiveData<Resource<List<Genre>>> loadGenre(){
        return genre;
    }

    public LiveData<Resource<List<MovieDetails>>> loadMovieList(){
        return movieDetails;
    }

    public void getMovieGenre(){
        final LiveData<Resource<List<Genre>>> repositorySource = repository.getGenreList();
        genre.addSource(repositorySource, new Observer<Resource<List<Genre>>>() {
            @Override
            public void onChanged(Resource<List<Genre>> listResource) {
                Log.d(TAG, "onChanged: Genre Details fetched ");
                genre.setValue(listResource);
            }
        });
    }

    public void getMovieList(int genre){
        final LiveData<Resource<List<MovieDetails>>> repositorySource = repository.getMovieDetails(genre);
        movieDetails.addSource(repositorySource, new Observer<Resource<List<MovieDetails>>>() {
            @Override
            public void onChanged(Resource<List<MovieDetails>> listResource) {
                movieDetails.setValue(listResource);
            }
        });
    }
}
