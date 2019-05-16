package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    private String genre_id;

    @SerializedName("name")
    private String genre_name;


    public String getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    @Override
    public String toString() {
        return("Genre: {genre_id: " + this.genre_id + ", genre_name: " + genre_name + "}");
    }
}
