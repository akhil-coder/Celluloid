package com.example.celluloid.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.celluloid.persistence.MovieGenreDao;
import com.example.celluloid.persistence.TmDbDatabase;
import com.example.celluloid.requests.ApiResponse;
import com.example.celluloid.requests.ServiceGenerator;
import com.example.celluloid.requests.responses.genre.Genre;
import com.example.celluloid.requests.responses.genre.GenreResponse;
import com.example.celluloid.requests.responses.movies.MovieDetails;
import com.example.celluloid.requests.responses.movies.MovieDiscoverResponse;
import com.example.celluloid.util.AppExecutors;
import com.example.celluloid.util.Constants;
import com.example.celluloid.util.NetworkBoundResource;
import com.example.celluloid.util.Resource;

import java.util.List;

public class TmDbRepository {
    private static final String TAG = "TmDbRepository";
    private static TmDbRepository instance;
    private MovieGenreDao movieGenreDao;

    public static TmDbRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TmDbRepository(context);
        }
        return instance;
    }

    public TmDbRepository(Context context) {
        this.movieGenreDao = TmDbDatabase.getInstance(context).getMovieGenreDao();
    }

    public LiveData<Resource<List<MovieDetails>>> getMovieDetails(final int genre) {
        return new NetworkBoundResource<List<MovieDetails>, MovieDiscoverResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull MovieDiscoverResponse item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieDetails> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<MovieDetails>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieDiscoverResponse>> createCall() {
                return ServiceGenerator.getTMDbApi().discoverMoviesList(Constants.api_key, genre);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Genre>>> getGenreList() {
        return new NetworkBoundResource<List<Genre>, GenreResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull GenreResponse item) {
                if (item.getGenreList() != null) {
                    Genre[] genres = new Genre[item.getGenreList().size()];
                    movieGenreDao.insertGenre(item.getGenreList().toArray(genres));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                return movieGenreDao.loadAllGenre();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenreResponse>> createCall() {
                return ServiceGenerator.getTMDbApi()
                        .getMovieGenreList(Constants.api_key);
            }
        }.getAsLiveData();
    }
}
