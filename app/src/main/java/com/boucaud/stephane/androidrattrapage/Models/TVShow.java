package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShow {
    @SerializedName("original_title")
    private String original_name;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("overview")
    private String overview;

    private String genresStringList;

    public TVShow(TVShowDetails TVDetails){
        this.original_name = TVDetails.getOriginal_name();
        this.id = TVDetails.getId();
        this.name = TVDetails.getName();
        this.vote_count = TVDetails.getVote_count();
        this.vote_average = TVDetails.getVote_average();
        this.poster_path = TVDetails.getPoster_path();
        this.first_air_date = TVDetails.getFirst_air_date();
        this.popularity = TVDetails.getPopularity();
        this.genre_ids = TVDetails.getGenresIds();
        this.original_language = TVDetails.getOriginal_language();
        this.backdrop_path = TVDetails.getBackdrop_path();
        this.overview = TVDetails.getOverview();
        this.genresStringList = TVDetails.getGenresStringList();
    }



    public String getOriginal_name() {
        return original_name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public float getPopularity() {
        return popularity;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getGenresStringList() {
        return genresStringList;
    }

    public String getPosterFullPath(){
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }
}
