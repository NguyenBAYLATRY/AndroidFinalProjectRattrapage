package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenresList {
    @SerializedName("genres")
    private List<Genre> genres;

    public int searchGenreID(String genreName){
        int res = 0;
        int length = genres.size();
        int i;

        for (i = 0; i<length; i++){
            if (genres.get(i).getGenre_name() == genreName){
                return Integer.parseInt(genres.get(i).getGenre_id());
            }
        }

        return res;
    }

    public List<String> getStringList(){
        int length = genres.size();
        int i;
        ArrayList<String> res = new ArrayList<String>();

        for (i = 0; i<length; i++){
            res.add(this.genres.get(i).getGenre_name());
        }

        return (List) res;
    }

    @Override
    public String toString() {
        int length = genres.size();
        int i;
        String res = new String();

        for (i = 0; i<length; i++){
            res += "- " + this.genres.get(i).getGenre_name() + "\n";
        }

        return (String) res;
    }
}
