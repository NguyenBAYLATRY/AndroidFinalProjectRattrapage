package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

public class EpisodeToAir {

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("episode_number")
    private int episode_number;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("production_code")
    private String production_code;

    @SerializedName("season_number")
    private int season_number;

    @SerializedName("show_id")
    private int show_id;

    @SerializedName("still_path")
    private String still_path;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("vote_count")
    private int vote_count;


    public String getAir_date() {
        return air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getProduction_code() {
        return production_code;
    }

    public int getSeason_number() {
        return season_number;
    }

    public int getShow_id() {
        return show_id;
    }

    public String getStill_path() {
        return still_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}
