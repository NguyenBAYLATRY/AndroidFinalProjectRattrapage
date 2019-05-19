package com.boucaud.stephane.androidrattrapage.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesList {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MoviesList(List<Movie> movies) {
        this.movies = movies;
    }

    public void add_Movie(Movie m){
        this.movies.add(m);
    }

    /**
     * Get the movies but only the ones mathing with a genre id
     * @param genre_filter : Integer representing a genre; used to filter movies
     * @return
     */
    public List<Movie> getMovies(int genre_filter) {
        ArrayList<Movie> res = new ArrayList<Movie>();
        int length = movies.size();
        int sub_length = 0;
        int i;
        int j = 0;
        Movie actual_movie;
        List<Integer> actual_genres;
        boolean found = false;

        for (i = 0; i<length; i++){
            actual_movie = this.movies.get(i);
            actual_genres = actual_movie.getGenre_ids();
            sub_length = actual_genres.size();
            found = false;
            j = 0;

            while (j <sub_length && !found){
                found = actual_genres.get(j) == genre_filter;
                j++;
            }
            if (found)
                res.add(actual_movie);
        }

        return (List) res;
    }

    public List<String> getStringTitlesList(){
        int length = movies.size();
        int i;
        ArrayList<String> res = new ArrayList<String>();

        for (i = 0; i<length; i++){
            res.add(this.movies.get(i).getTitle());
        }

        return (List) res;
    }

    @Override
    public String toString() {
        int length = movies.size();
        int i;
        String res = new String();

        for (i = 0; i<length; i++){
            res += "- " + this.movies.get(i).toString() + "\n";
        }

        return (String) res;
    }
}
