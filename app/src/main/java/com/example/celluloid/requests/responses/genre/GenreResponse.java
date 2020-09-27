package com.example.celluloid.requests.responses.genre;


import java.util.List;

public class GenreResponse {
    private List<Genre> genres;

    public GenreResponse(List<Genre> genreList) {
        this.genres = genreList;
    }

    public List<Genre> getGenreList() {
        return genres;
    }

    @Override
    public String toString() {
        return "GenreResponse{" +
                "genreList=" + genres +
                '}';
    }
}
