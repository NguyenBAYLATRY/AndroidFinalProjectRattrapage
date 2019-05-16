package com.boucaud.stephane.androidrattrapage.Controllers;

import com.boucaud.stephane.androidrattrapage.APIMovieDB.APIMovieDB;
import com.boucaud.stephane.androidrattrapage.Models.GenresList;
import com.boucaud.stephane.androidrattrapage.Models.TVShowsList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is an abstract class because it will be implemented for different requests types.
 * We can consider this abstract class as the API for APIMovieDB
 */
public class Controller implements ControllerInterface{

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private APIMovieDB API;
    private String API_KEY;
    private String LANG;


    public Controller(String API_KEY, String LANG) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(APIMovieDB.class);

        this.API_KEY = API_KEY;
        this.LANG = LANG;
    }

    public void queryGetVideoGenres(Callback actions){
        Call<GenresList> call = this.API.getVideoGenres(this.API_KEY, this.LANG);
        call.enqueue(actions);
    }

    public void queryGetTVGenres(Callback actions){
        Call<GenresList> call = this.API.getTVGenres(this.API_KEY, this.LANG);
        call.enqueue(actions);
    }

    public void querySearchTVShows(int page, boolean include_adult, String query, Callback actions){
        Call<TVShowsList> call = this.API.searchTVShows(this.API_KEY, this.LANG, page, include_adult, query);
        call.enqueue(actions);
    }

    /*
        TO COMPLETE LATER

    public void querySearchMovies(int page, boolean include_adult, String query, Callback actions){
        Call<MoviesList> call = this.API.searchMovies(this.API_KEY, this.LANG, page, include_adult, query);
        call.enqueue(actions);
    }

    public void queryMovieDetails(int movie_id, String api_key, String language, Callback actions){
        Call<MovieDetails> call = this.API.getMovieDetails(movie_id, this.API_KEY, this.LANG);
        call.enqueue(actions);
    }

    public void queryMovieTrends(String period, Callback actions){
        Call<MoviesList> call = this.API.getMovieTrends(period, this.API_KEY);
        call.enqueue(actions);
    }

    */


    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

}
