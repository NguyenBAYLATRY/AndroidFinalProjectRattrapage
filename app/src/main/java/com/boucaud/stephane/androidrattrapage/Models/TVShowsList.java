package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TVShowsList {

    @SerializedName("results")
    private List<TVShow> tv_shows;

    public List<TVShow> getTVShows() {
        return tv_shows;
    }

    public TVShowsList(List<TVShow> tv_shows) {
        this.tv_shows = tv_shows;
    }

    public void add_TV_Show(TVShow m){
        this.tv_shows.add(m);
    }

    /**
     * Get the movies but only the ones mathing with a genre id
     * @param genre_filter : Integer representing a genre; used to filter movies
     * @return
     */
    public List<TVShow> getTVShows(int genre_filter) {
        ArrayList<TVShow> res = new ArrayList<TVShow>();
        int length = tv_shows.size();
        int sub_length = 0;
        int i;
        int j = 0;
        TVShow actual_tv_show;
        List<Integer> actual_genres;
        boolean found = false;

        for (i = 0; i<length; i++){
            actual_tv_show = this.tv_shows.get(i);
            actual_genres = actual_tv_show.getGenre_ids();
            sub_length = actual_genres.size();
            found = false;
            j = 0;

            while (j <sub_length && !found){
                found = actual_genres.get(j) == genre_filter;
                j++;
            }
            if (found)
                res.add(actual_tv_show);
        }

        return (List) res;
    }

    public List<String> getStringNamesList(){
        int length = tv_shows.size();
        int i;
        ArrayList<String> res = new ArrayList<String>();

        for (i = 0; i<length; i++){
            res.add(this.tv_shows.get(i).getName());
        }

        return (List) res;
    }

    @Override
    public String toString() {
        int length = tv_shows.size();
        int i;
        String res = new String();

        for (i = 0; i<length; i++){
            res += "- " + this.tv_shows.get(i).toString() + "\n";
        }

        return (String) res;
    }
}
