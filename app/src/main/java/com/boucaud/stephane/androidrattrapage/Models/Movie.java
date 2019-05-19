package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    private String genresStringList;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    public Movie(MovieDetails movieDetails){
        this.vote_count = movieDetails.getVote_count();
        this.id = movieDetails.getId();
        this.video = movieDetails.isVideo();
        this.vote_average = movieDetails.getVote_average();
        this.title = movieDetails.getTitle();
        this.popularity = movieDetails.getPopularity();
        this.poster_path = movieDetails.getPoster_path();
        this.original_language = movieDetails.getOriginal_language();
        this.original_title = movieDetails.getOriginal_title();
        this.genresStringList = movieDetails.getGenresStringList();
        this.backdrop_path = movieDetails.getBackdrop_path();
        this.adult = movieDetails.isAdult();
        this.overview = movieDetails.getOverview();
        this.release_date = movieDetails.getRelease_date();
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getGenresStringList() {
        return genresStringList;
    }

    public String getPosterFullPath(){
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "vote_count=" + vote_count +
                ", id=" + id +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", genre_ids=" + genre_ids +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
