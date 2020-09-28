package com.example.celluloid.requests.responses.movies;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "moviedetails")
public class MovieDetails {

    @NonNull
    @PrimaryKey
    private int id;

    private String release_date;
    private String overview;
    private double vote_average;
    private String title;
//    private List<Integer> genre_ids;
    private String original_title;
    private String originalLanguage;
    private String backdrop_path;
    private boolean adult;
    private String poster_path;
    private boolean video;
    private int vote_count;
    private double popularity;

    public MovieDetails(int id, String release_date, String overview, double vote_average, String title, String original_title, String originalLanguage, String backdrop_path, boolean adult, String poster_path, boolean video, int vote_count, double popularity) {
        this.id = id;
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
        this.title = title;
        this.original_title = original_title;
        this.originalLanguage = originalLanguage;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.poster_path = poster_path;
        this.video = video;
        this.vote_count = vote_count;
        this.popularity = popularity;
    }

    public MovieDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
