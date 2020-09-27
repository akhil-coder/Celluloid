package com.example.celluloid.requests;


import androidx.lifecycle.LiveData;

import com.example.celluloid.requests.responses.genre.GenreResponse;
import com.example.celluloid.requests.responses.movies.MovieDiscoverResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {
    // Get Genre
    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse>> getMovieGenreList(
            @Query("api_key") String api_key
    );

    @GET("discover/movie")
    LiveData<ApiResponse<MovieDiscoverResponse>> discoverMoviesList(
            @Query("api_key") String apiKey,
            @Query("with_genres") int genres
    );
}
