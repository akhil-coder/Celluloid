package com.example.celluloid.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.celluloid.requests.responses.movies.MovieDetails;

import java.util.List;

@Dao
public interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieDetails(MovieDetails... movieDetails);

    @Query("SELECT * FROM moviedetails")
    public LiveData<List<MovieDetails>> loadMovieDetails();
}
