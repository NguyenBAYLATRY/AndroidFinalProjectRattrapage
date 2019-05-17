package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TVShowDetails {

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("created_by")
    private List<Creator> created_by;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private int id;

    @SerializedName("in_production")
    private boolean in_production;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("last_air_date")
    private String last_air_date;

    @SerializedName("last_episode_to_air")
    private EpisodeToAir last_episode_to_air;

    @SerializedName("name")
    private String name;

    @SerializedName("next_episode_to_air")
    private EpisodeToAir next_episode_to_air;

    @SerializedName("networks")
    private List<Network> networks;

    @SerializedName("number_of_episodes")
    private int number_of_episodes;

    @SerializedName("number_of_seasons")
    private int number_of_seasons;

    @SerializedName("origin_country")
    private List<String> origin_country;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("production_companies")
    private List<ProductionCompany> production_companies;

    @SerializedName("seasons")
    private List<Season> seasons;

    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("vote_count")
    private int vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Creator> getCreated_by() {
        return created_by;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public EpisodeToAir getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public String getName() {
        return name;
    }

    public EpisodeToAir getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_name() {
        return original_name;
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

    public List<Season> getSeasons() {
        return seasons;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
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

    public List<Integer> getGenresIds(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0;
        for (i = 0; i<genres.size(); i++){
            if (genres.get(i).getGenre_id() != null){
                res.add(new Integer(genres.get(i).getGenre_id()));
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

}
