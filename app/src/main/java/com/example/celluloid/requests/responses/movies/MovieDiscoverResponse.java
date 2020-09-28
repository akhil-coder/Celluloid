package com.example.celluloid.requests.responses.movies;

import java.util.List;

public  class MovieDiscoverResponse {
    private List<MovieDetails> results;
    private int totalPages;
    private int totalResults;
    private int page;

    public MovieDiscoverResponse(List<MovieDetails> results, int totalPages, int totalResults, int page) {
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.page = page;
    }

    public List<MovieDetails> getResults() {
        return results;
    }

    public void setResults(List<MovieDetails> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
