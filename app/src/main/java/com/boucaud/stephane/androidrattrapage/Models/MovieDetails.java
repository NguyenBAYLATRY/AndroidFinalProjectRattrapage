package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("budget")
    private int budget;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("id")
    private int id;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("production_companies")
    private List<ProductionCompany> production_companies;

    @SerializedName("production_countries")
    private List<ProductionCountry> production_countries;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spoken_languages;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("title")
    private String title;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("vote_count")
    private int vote_count;

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public List<ProductionCountry> getProduction_countries() {
        return production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<SpokenLanguage> getSpoken_languages() {
        return spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPosterFullPath(){
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    public String getGenresStringList(){
        String res = "";
        for (int i = 0; i < genres.size(); i++){
            if (res.length()>0){
                res += ", " + genres.get(i).getGenre_name();
            }
            else{
                res += genres.get(i).getGenre_name();
            }
        }
        return res;
    }

    public String getProductionCompaniesStringList(){
        String res = "";
        for (int i = 0; i < production_companies.size(); i++){
            if (res.length()>0){
                res += ", " + production_companies.get(i).getName();
            }
            else{
                res += production_companies.get(i).getName();
            }
        }
        return res;
    }

    public String getProductionCountriesStringList(){
        String res = "";
        for (int i = 0; i < production_countries.size(); i++){
            if (res.length()>0){
                res += ", " + production_countries.get(i).getName();
            }
            else{
                res += production_countries.get(i).getName();
            }
        }
        return res;
    }

    public String getSpokenLanguagesStringList(){
        String res = "";
        for (int i = 0; i < spoken_languages.size(); i++){
            if (res.length()>0){
                res += ", " + spoken_languages.get(i).getName();
            }
            else{
                res += spoken_languages.get(i).getName();
            }
        }
        return res;
    }
}
