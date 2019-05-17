package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

public class Creator {

    @SerializedName("id")
    private int id;

    @SerializedName("credit_id")
    private String credit_id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profile_path;

    public int getId() {
        return id;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
