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
import com.example.celluloid.requests.responses.Genre;
import com.example.celluloid.requests.responses.GenreResponse;
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

    public LiveData<Resource<List<Genre>>> getGenreList() {
        return new NetworkBoundResource<List<Genre>, GenreResponse>(AppExecutors.getInstance()){

            @Override
            protected void saveCallResult(@NonNull GenreResponse item) {
                if(item.getGenreList() != null) {
                    Log.d(TAG, "saveCallResult: " + item.getGenreList().get(0).toString());
                    Genre[] genres = new Genre[item.getGenreList().size()];
                    movieGenreDao.insertGenre(item.getGenreList().toArray(genres));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return true;
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
