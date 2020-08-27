package com.example.celluloid.requests;


import androidx.lifecycle.LiveData;

import com.example.celluloid.requests.responses.GenreResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {
    // Get Genre
    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse>> getMovieGenreList(
            @Query("api_key") String api_key);
}
