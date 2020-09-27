package com.example.celluloid.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.celluloid.requests.responses.movies.MovieDetails;

@Dao
public interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieDetails(MovieDetails movieDetails);

    @Query("SELECT * FROM moviedetails")
    public void loadMovieDetails(MovieDetails movieDetails);
}
