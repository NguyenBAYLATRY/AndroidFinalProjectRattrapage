package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("known_for_department")
    private String known_for_department;

    @SerializedName("deathday")
    private String deathday;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("also_known_as")
    private List<String> also_known_as;

    @SerializedName("gender")
    private int gender;

    @SerializedName("biography")
    private String biography;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("place_of_birth")
    private String place_of_birth;

    @SerializedName("profile_path")
    private String profile_path;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("homepage")
    private String homepage;

    public String getBirthday() {
        return birthday;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getDeathday() {
        return deathday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public int getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImageFullPath(){
        return "https://image.tmdb.org/t/p/w500" + profile_path;
    }

    public String getAlso_known_as_String(){
        String res = "";
        for (int i = 0; i < also_known_as.size(); i++){
            if (res.length()>0){
                res += ", " + also_known_as.get(i);
            }
            else{
                res += also_known_as.get(i);
            }
        }
        return res;
    }
}
